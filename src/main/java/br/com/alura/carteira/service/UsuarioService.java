package br.com.alura.carteira.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.dto.UsuarioOutputDto;
import br.com.alura.carteira.modelo.Usuario;

@Service
public class UsuarioService {

	private List<Usuario> usuarios = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<UsuarioOutputDto> listar() {
		return usuarios.stream().map(u -> modelMapper.map(u, UsuarioOutputDto.class)).collect(Collectors.toList());
	}

	public void cadastrar(UsuarioFormDto usuarioFormDto) {
		Usuario usuario = modelMapper.map(usuarioFormDto, Usuario.class);
		
		String senha = new Random().nextInt(999999) +"";
		usuario.setSenha(senha);
		
		usuarios.add(usuario);
	}
	
}
