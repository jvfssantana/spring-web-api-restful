package io.github.jvfssantana.rest.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

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

}
