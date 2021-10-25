package br.com.alura.carteira.service;

import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.AtualizacaoUsuarioFormDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.dto.UsuarioOutputDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Page<UsuarioOutputDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(u -> modelMapper.map(u, UsuarioOutputDto.class));
	}

	@Transactional
	public UsuarioOutputDto cadastrar(UsuarioFormDto usuarioFormDto) {
		Usuario usuario = modelMapper.map(usuarioFormDto, Usuario.class);
		
		String senha = new Random().nextInt(999999) +"";
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));
		
		usuarioRepository.save(usuario);
		return modelMapper.map(usuario, UsuarioOutputDto.class);
	}

	@Transactional
	public UsuarioOutputDto atualizar(AtualizacaoUsuarioFormDto usuarioFormDto) {
		Usuario usuario = usuarioRepository.getById(usuarioFormDto.getId());
		
		usuario.atualizarInformacoes(usuarioFormDto.getNome(), usuarioFormDto.getLogin(), bCryptPasswordEncoder.encode(usuarioFormDto.getSenha()));
		
		return modelMapper.map(usuario, UsuarioOutputDto.class);
	}

	@Transactional
	public void remover(Long id) {
		usuarioRepository.deleteById(id);
	}

	public UsuarioOutputDto detalhar(Long id) {
		Usuario usuario = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(usuario, UsuarioOutputDto.class);
	}
	
}
