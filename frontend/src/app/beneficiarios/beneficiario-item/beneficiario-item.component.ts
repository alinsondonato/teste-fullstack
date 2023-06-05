import { Component, OnInit } from '@angular/core';
import { Beneficiario } from '../beneficiario.model';
import { ActivatedRoute, Router } from '@angular/router';
import { BeneficiarioService } from '../beneficiario.service';
import { SelectItem } from 'primeng/api';
import { DropdownModule } from 'primeng/dropdown';
import { PlanoService } from 'src/app/planos/plano.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Plano } from 'src/app/planos/plano.model';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-beneficiario-item',
  templateUrl: './beneficiario-item.component.html',
  styleUrls: ['./beneficiario-item.component.css']
})
export class BeneficiarioItemComponent implements OnInit {
  beneficiario: Beneficiario | undefined;
  planos: SelectItem[] = [];
  carregando = false;
  temErro = false;
  messages: Message[] =  [{ severity: 'error', summary: 'Erro', detail: '' }];

  formGroup = this.formBuilder.group({
    nome: ['', [Validators.required]],
    cpf: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    idade: [''],
    plano: ['']
  });

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private beneficiarioService: BeneficiarioService,
    private planoService: PlanoService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.carregando = true;
    this.planoService.getPlanos().subscribe(
      (resp) => {
        this.planos = resp.map(plano => ({ label: plano.nome, value: plano }));
      });
    if (id) {
      this.beneficiarioService.getBeneficiarioById(parseInt(id)).subscribe(beneficiario => {
        this.carregando = false;
        this.beneficiario = beneficiario;
        });
    } else {
      this.carregando = false;
      this.beneficiario = new Beneficiario();
      this.beneficiario.plano = new Plano();
    }
  }

  salvarBeneficiario() {
    this.carregando = true;
    if (this.beneficiario?.id) {
      this.beneficiarioService.atualizarBeneficiario(this.beneficiario).subscribe(
        () => {
          this.carregando = false;
          this.router.navigate(['/beneficiarios']);
        },
        (error) => {
          this.carregando = false;
          this.messages[0].detail = (error.error.fieldsMessage[0]);
          this.temErro = true;
        }
      );
    } else if (this.beneficiario) {
      this.beneficiarioService.adicionarBeneficiario(this.beneficiario).subscribe(() => {
        this.carregando = false;
        this.router.navigate(['/beneficiarios']);
      });
    }
  }

  voltar() {
    
    console.log(this.formGroup.value);
    this.beneficiario = undefined;
    this.router.navigate(['/beneficiarios']);
  }

  teste() {
    console.log(this.formGroup.value);
    
  }
}
