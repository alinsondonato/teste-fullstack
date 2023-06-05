import { Component, OnInit } from '@angular/core';
import { Plano } from '../plano.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PlanoService } from 'src/app/planos/plano.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-plano-item',
  templateUrl: './plano-item.component.html',
  styleUrls: ['./plano-item.component.css']
})
export class PlanoItemComponent implements OnInit {

  plano: Plano | undefined;
  carregando = false;
  temErro = false;
  messages: Message[] =  [{ severity: 'error', summary: 'Erro', detail: '' }];

  formGroup = this.formBuilder.group({
    nome: ['', [Validators.required]],
    valor: ['', [Validators.required]]
  });

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private planoService: PlanoService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.planoService.getPlanoById(parseInt(id)).subscribe(plano => {
        this.plano = plano;
        });
    } else {
      this.plano = new Plano();
    }
  }

  salvarPlano() {
    if (this.plano?.id) {
      this.planoService.atualizarPlano(this.plano).subscribe(() => {
        this.router.navigate(['/planos']);
      });
    } else if (this.plano) {
      this.planoService.adicionarPlano(this.plano).subscribe(() => {
        this.router.navigate(['/planos']);
      });
    }
  }

  voltar() {
    this.plano = undefined;
    this.router.navigate(['/planos']);
  }
}
