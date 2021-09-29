package br.com.alura.carteira.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.dto.UsuarioOutputDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<UsuarioOutputDto> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(u -> modelMapper.map(u, UsuarioOutputDto.class)).collect(Collectors.toList());
	}

	public void cadastrar(UsuarioFormDto usuarioFormDto) {
		Usuario usuario = modelMapper.map(usuarioFormDto, Usuario.class);
		
		String senha = new Random().nextInt(999999) +"";
		usuario.setSenha(senha);
		
		usuarioRepository.save(usuario);
	}
	
}
