import { Component, OnInit } from '@angular/core';
import { Beneficiario } from '../beneficiario.model';
import { BeneficiarioService } from '../beneficiario.service';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-beneficiario-list',
  templateUrl: './beneficiario-list.component.html',
  styleUrls: ['./beneficiario-list.component.css'],
  providers: [MessageService]
})
export class BeneficiarioListComponent implements OnInit {

  beneficiarios: Beneficiario[] = [];
  
  constructor(
    private beneficiarioService: BeneficiarioService,
    private router: Router,
    private messageService: MessageService
    ) { }

  ngOnInit(): void {
    this.carregarBeneficiarios();
  }

  carregarBeneficiarios(): void {
    this.beneficiarioService.getBeneficiarios().subscribe(
      beneficiarios => this.beneficiarios = beneficiarios,
      error => console.log(error)
    );
  }

  carregarBeneficiario(id: number) {
    this.router.navigate(['/beneficiarios', id]);
  }
  
  excluirBeneficiario(id: number) {
    if (confirm('Deseja realmente excluir o beneficiario?')) {
      this.beneficiarioService.excluirBeneficiario(id).subscribe(
        () => {
          this.showMsg();
          this.carregarBeneficiarios();
        },
        (error) => {
          console.error('Erro ao excluir neneficiario:', error);
        }
      );
    }
  }
  
  adicionarBeneficiario() {
    this.router.navigate(['/beneficiarios/novo']);
  }

  showMsg() {
    this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Beneficiário excluído com sucesso' });
  }

}
