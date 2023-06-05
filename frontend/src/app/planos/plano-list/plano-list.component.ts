import { Component, OnInit } from '@angular/core';
import { PlanoService } from '../plano.service';
import { Plano } from '../plano.model';
import { Router } from '@angular/router';

export class FiltroPlano {
  nome: string;
}

@Component({
  selector: 'app-plano-list',
  templateUrl: './plano-list.component.html',
  styleUrls: ['./plano-list.component.css']
})
export class PlanoListComponent implements OnInit {

  planos: Plano[] = [];
  filtro: FiltroPlano;
  
  constructor(
    private planoService: PlanoService,
    private router: Router
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
          this.carregarPlanos();
        },
        (error) => {
          console.error('Erro ao excluir plano:', error);
        }
      );
    }
  }
  

}
