package io.github.jvfssantana.rest.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.*;

import io.github.jvfssantana.entity.Usuario;
import io.github.jvfssantana.service.implementacao.UsuarioServiceImplementacao;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {	
	
	private final UsuarioServiceImplementacao usuarioImplementacao;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Usuario salvarUsuario(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioImplementacao.salvarUsuario(usuario);
	}
}
