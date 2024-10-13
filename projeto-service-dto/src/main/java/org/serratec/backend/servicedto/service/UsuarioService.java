package org.serratec.backend.servicedto.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.servicedto.domain.Usuario;
import org.serratec.backend.servicedto.exception.EmailException;
import org.serratec.backend.servicedto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> buscar(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public Usuario inserir(Usuario user) throws EmailException {
		Usuario usuario = usuarioRepository.findByEmail(user.getEmail());
		if (usuario != null) {
			throw new EmailException("Email já existente");
		}
		return usuarioRepository.save(user);
	}
	
}