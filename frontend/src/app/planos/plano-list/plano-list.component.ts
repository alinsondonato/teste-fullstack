import { Component, OnInit } from '@angular/core';
import { PlanoService } from '../plano.service';
import { Plano } from '../plano.model';
import { Router } from '@angular/router';
import { Message } from 'primeng/api';
import { MessageService } from 'primeng/api';

export class FiltroPlano {
  nome: string;
}

@Component({
  selector: 'app-plano-list',
  templateUrl: './plano-list.component.html',
  styleUrls: ['./plano-list.component.css'],
  providers: [MessageService]
})
export class PlanoListComponent implements OnInit {

  planos: Plano[] = [];
  filtro: FiltroPlano;
  messages: Message[] =  [{ severity: 'success', summary: 'Sucesso', detail: 'Plano salvo com sucesso.' }];
  
  constructor(
    private planoService: PlanoService,
    private router: Router,
    private messageService: MessageService
    ) { }

  ngOnInit(): void {
    this.carregarPlanos();
  }

  carregarPlanos(): void {
    this.planoService.getPlanos().subscribe(
      planos => this.planos = planos,
      error => console.log(error)
    );
  }

  carregarPlano(id: number) {
    this.router.navigate(['/planos', id]);
  }

  adicionarPlano() {
    this.router.navigate(['/planos/novo']);
  }
  
  excluirPlano(id: number) {
    if (confirm('Deseja realmente excluir o plano?')) {
      this.planoService.excluirPlano(id).subscribe(
        () => {
          this.showMsg();
          this.carregarPlanos();
        },
        (error) => {
          console.error('Erro ao excluir plano:', error);
        }
      );
    }
  }

  showMsg() {
    this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Plano excluído com sucesso' });
  }

}
