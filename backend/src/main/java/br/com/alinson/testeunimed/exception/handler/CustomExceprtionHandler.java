package br.com.alinson.testeunimed.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alinson.testeunimed.exception.ArgumentoInvalidoException;
import br.com.alinson.testeunimed.exception.ErroDetalhes;
import br.com.alinson.testeunimed.exception.ValidacaoDetalhes;

@RestControllerAdvice
public class CustomExceprtionHandler {
	
	@ExceptionHandler(ArgumentoInvalidoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDetalhes handleArgumentNotValidException(
    		ArgumentoInvalidoException exception) {
    	ErroDetalhes erroDetalhes = ErroDetalhes.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Argumento inválido")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
        return erroDetalhes;
    }
    
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErroDetalhes handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        List<String> fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.toList());
        List<String> fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return ValidacaoDetalhes.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field Validation Error")
                .detail("Check the field(s) below")
                .developerMessage(exception.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build();
    }

}
