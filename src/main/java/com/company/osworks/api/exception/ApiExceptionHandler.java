package com.company.osworks.api.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.company.osworks.api.handler.Problema;
import com.company.osworks.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		 List<Problema.Objeto> objetos = ex.getBindingResult()
				 	.getAllErrors()
				 		.stream()
				 			.map(o -> {
				 			Problema.Objeto objeto = new Problema.Objeto();
				 			objeto.setPropriedade(((FieldError)o).getField());
				 			objeto.setDetalhe(o.getDefaultMessage());
				 			return objeto;
				 		}).collect(Collectors.toList());
		
		
		Problema response = new Problema();
		response.setStatus(status.value());
		response.setTimestamp(LocalDateTime.now());
		response.setTitulo("Um ou mais campos estão incorretos. Por favor corrija(m)-o(s) e tente novamente.");
		response.setObjetos(objetos);
		
		return handleExceptionInternal(ex, response, headers, status, request);
	}
	
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(
			EntityNotFoundException ex, WebRequest request) {
		
		Problema response = new Problema();
		response.setStatus(404);
		response.setTimestamp(LocalDateTime.now());
		response.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	protected ResponseEntity<Object> handleNegocio(
			NegocioException ex, WebRequest request) {
		
		Problema response = new Problema();
		response.setStatus(400);
		response.setTimestamp(LocalDateTime.now());
		response.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
}
