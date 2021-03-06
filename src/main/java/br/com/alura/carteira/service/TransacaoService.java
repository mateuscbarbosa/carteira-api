package br.com.alura.carteira.service;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
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
	
	@Autowired
	private CalculadoraDeImpostoService calculadoraDeImpostoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<TransacaoOutputDto> listar(Pageable paginacao, Usuario usuario) {
		return transacaoRepository
				.findAllByUsuario(paginacao, usuario)
				.map(t -> modelMapper.map(t, TransacaoOutputDto.class));
//		Page<Transacao> transacoes = transacaoRepository.findAllByUsuario(paginacao, usuario);
//		
//		List<TransacaoOutputDto> transacoesDto = new ArrayList<>();
//		transacoes.forEach(transacao -> {
//			BigDecimal imposto = calculadoraDeImpostoService.calcular(transacao);
//			TransacaoOutputDto dto = modelMapper.map(transacao, TransacaoOutputDto.class);
//			dto.setImposto(imposto);
//			transacoesDto.add(dto);
//		});
//		
//		return new PageImpl<TransacaoOutputDto>(transacoesDto, transacoes.getPageable(),transacoes.getTotalElements());
	}

	@Transactional
	public TransacaoOutputDto cadastrar(TransacaoFormDto transasaoFormDto, Usuario logado) {
		Long idUsuario = transasaoFormDto.getUsuarioId();
		
		try {
			Usuario usuario = usuarioRepository.getById(idUsuario);
			
			if(!usuario.equals(logado)) {
				lancarExceptionAcessoNegado();
			}
			
			Transacao transacao = modelMapper.map(transasaoFormDto, Transacao.class);
			transacao.setId(null);
			transacao.setUsuario(usuario);
			BigDecimal imposto = calculadoraDeImpostoService.calcular(transacao);
			transacao.setImposto(imposto);
			
			transacaoRepository.save(transacao);
			
			return modelMapper.map(transacao, TransacaoOutputDto.class);
		}catch(EntityNotFoundException e) {
			throw new IllegalArgumentException("Usu??rio inexistente.");
		}
		
	}

	@Transactional
	public TransacaoOutputDto atualizar(AtulizacaoTransacaoFormDto transacaoFormDto, Usuario logado) {
		Transacao transacao = transacaoRepository.getById(transacaoFormDto.getId());
		
		if(!transacao.pertenceAoUsuario(logado)) {
			lancarExceptionAcessoNegado();
		}
		
		transacao.atualizarInformacoes(transacaoFormDto.getTicker(), transacaoFormDto.getData(), transacaoFormDto.getPreco(), transacaoFormDto.getQuantidade(), transacaoFormDto.getTipo());
		
		return modelMapper.map(transacao, TransacaoOutputDto.class);
	}

	@Transactional
	public void remover(Long id, Usuario logado) {
		Transacao transacao = transacaoRepository.getById(id);
		
		if(!transacao.pertenceAoUsuario(logado)) {
			lancarExceptionAcessoNegado();
		}
		
		transacaoRepository.deleteById(id);
	}
	
	public TransacaoDetalhadaOutputDto detalhar(Long id, Usuario logado) {
		Transacao transacao = transacaoRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		if(!transacao.pertenceAoUsuario(logado)) {
			lancarExceptionAcessoNegado();
		}
		
		return modelMapper.map(transacao, TransacaoDetalhadaOutputDto.class);
	}
	
	private void lancarExceptionAcessoNegado() {
		throw new AccessDeniedException("Acesso negado!");
	}
	
}
