import { Plano } from "../planos/plano.model";

export class Beneficiario {
  id: number;
  nome: string;
  cpf: string;
  email: string;
  idade: number;
  plano?: Plano;
}