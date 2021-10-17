package br.com.alura.carteira.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.dto.TransacaoOutputDto;
import br.com.alura.carteira.modelo.Transacao;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/transacoesantiga")
@ApiIgnore
public class TransacaoControllerAntiga {

	private List<Transacao> transacoes = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping
	public List<TransacaoOutputDto> listar() {
//		//return new Transacao("XPTO1", LocalDate.now(), new BigDecimal("10.2"),29, TipoTransacao.COMPRA);
//		List<TransacaoOutputDto> transacoesDto = new ArrayList<>();
//		for (Transacao transacao : transacoes) {
//			TransacaoOutputDto dto = new TransacaoOutputDto();
//			dto.setTicker(transacao.getTicker());
//			dto.setPreco(transacao.getPreco());
//			dto.setQuantidade(transacao.getQuantidade());
//			dto.setTipo(transacao.getTipo());
//			
//			transacoesDto.add(dto);
//		}
//		
//		return transacoesDto;
//		return transacoes.stream().map(TransacaoOutputDto::new).collect(Collectors.toList());
		
		return transacoes.stream().map(t -> modelMapper.map(t, TransacaoOutputDto.class)).collect(Collectors.toList());
		
	}
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid TransacaoFormDto transacaoFormDto) {
		Transacao transacao = modelMapper.map(transacaoFormDto, Transacao.class);
		
		transacoes.add(transacao);
	}
}
