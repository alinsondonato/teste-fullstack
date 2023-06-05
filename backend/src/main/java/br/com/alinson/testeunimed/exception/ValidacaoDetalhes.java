package br.com.alinson.testeunimed.exception;

import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidacaoDetalhes extends ErroDetalhes {

	private List<String> fields;
	private List<String> fieldsMessage;

}
