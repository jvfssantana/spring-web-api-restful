package io.github.jvfssantana.service.implementacao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.jvfssantana.entity.Usuario;
import io.github.jvfssantana.repository.UsuarioRepository;

@Service
public class UsuarioServiceImplementacao implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired		
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado na base de dados!"));
		
		String[] roles = usuario.isAdmin() ? 
				new String[]{"ADMIN", "USER"} : new String[]{"USER"};
		
		return User
				.builder() 
				.username(usuario.getLogin())
				.username(usuario.getSenha())
				.roles(roles)
				.build();
	}

}
