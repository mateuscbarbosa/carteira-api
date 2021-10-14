package br.com.alura.carteira.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.dto.TransacaoOutputDto;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<TransacaoOutputDto> listar(Pageable paginacao) {
		Page<Transacao> transacoes = transacaoRepository.findAll(paginacao);
		return transacoes.map(t -> modelMapper.map(t, TransacaoOutputDto.class));
	}

	@Transactional
	public TransacaoOutputDto cadastrar(TransacaoFormDto transasaoFormDto) {
		Long idUsuario = transasaoFormDto.getUsuarioId();
		
		try {
			Usuario usuario = usuarioRepository.getById(idUsuario);
			
			Transacao transacao = modelMapper.map(transasaoFormDto, Transacao.class);
			transacao.setId(null);
			transacao.setUsuario(usuario);
			
			transacaoRepository.save(transacao);
			
			return modelMapper.map(transacao, TransacaoOutputDto.class);
		}catch(EntityNotFoundException e) {
			throw new IllegalArgumentException("Usuário inexistente.");
		}
		
	}
	
	
}
