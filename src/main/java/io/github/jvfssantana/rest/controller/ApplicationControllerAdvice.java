package io.github.jvfssantana.rest.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.stream.Collectors;

import io.github.jvfssantana.exception.PedidoNaoEncontradoException;
import io.github.jvfssantana.exception.RegrasNegocioException;
import io.github.jvfssantana.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RegrasNegocioException.class)
	@ResponseStatus(BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegrasNegocioException exception) {
		String mensagemErro = exception.getMessage();
		return new ApiErrors(mensagemErro);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(NOT_FOUND)
	public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException exception) {
		return new ApiErrors(exception.getMessage());
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(BAD_REQUEST)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException argumentNotValidException) {
		List<String> errors = argumentNotValidException.getBindingResult().getAllErrors().stream().map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());
		return new 	ApiErrors(errors);
	}
	
	
	
}
