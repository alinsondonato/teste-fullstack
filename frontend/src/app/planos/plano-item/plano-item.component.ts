import { Component, OnInit } from '@angular/core';
import { Plano } from '../plano.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PlanoService } from 'src/app/planos/plano.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Message } from 'primeng/api';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-plano-item',
  templateUrl: './plano-item.component.html',
  styleUrls: ['./plano-item.component.css'],
  providers: [MessageService]
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
    private formBuilder: FormBuilder,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.carregando = true;
      this.planoService.getPlanoById(parseInt(id)).subscribe(plano => {
        this.carregando = false;
        this.plano = plano;
        });
    } else if(!this.plano) {
      this.plano = new Plano();
    }
  }

  salvarPlano() {
    if (this.plano?.id) {
      this.carregando = true;
      this.planoService.atualizarPlano(this.plano).subscribe(() => {
        this.carregando = false;
        this.showMsg();
        setTimeout(() => {
          this.router.navigate(['/planos']);
        }, 1000);
      });
    } else if (this.plano) {
      this.carregando = true;
      this.planoService.adicionarPlano(this.plano).subscribe(() => {
        this.carregando = false;
        this.showMsg();
        setTimeout(() => {
          this.router.navigate(['/planos']);
        }, 1000);
      });
    }
  }

  voltar() {
    this.plano = undefined;
    this.router.navigate(['/planos']);
  }

  showMsg() {
    this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Plano salvo com sucesso' });
  }
}
