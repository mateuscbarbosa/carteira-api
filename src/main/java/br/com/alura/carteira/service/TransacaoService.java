package br.com.alura.carteira.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.AtulizacaoTransacaoFormDto;
import br.com.alura.carteira.dto.TransacaoDetalhadaOutputDto;
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

	@Transactional
	public TransacaoOutputDto atualizar(AtulizacaoTransacaoFormDto transacaoFormDto) {
		Transacao transacao = transacaoRepository.getById(transacaoFormDto.getId());
		
		transacao.atualizarInformacoes(transacaoFormDto.getTicker(), transacaoFormDto.getData(), transacaoFormDto.getPreco(), transacaoFormDto.getQuantidade(), transacaoFormDto.getTipo());
		
		return modelMapper.map(transacao, TransacaoOutputDto.class);
	}

	@Transactional
	public void remover(Long id) {
		transacaoRepository.deleteById(id);
	}
	
	public TransacaoDetalhadaOutputDto detalhar(@NotNull Long id) {
		Transacao transacao = transacaoRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(transacao, TransacaoDetalhadaOutputDto.class);
	}
	
	
}
