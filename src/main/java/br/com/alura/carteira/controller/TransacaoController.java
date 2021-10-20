package br.com.alura.carteira.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.carteira.dto.AtulizacaoTransacaoFormDto;
import br.com.alura.carteira.dto.TransacaoDetalhadaOutputDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.dto.TransacaoOutputDto;
import br.com.alura.carteira.service.TransacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transacoes")
@Api(tags = "Transações")
public class TransacaoController {

	@Autowired
	private TransacaoService service;
	
	@GetMapping
	@ApiOperation("Listar Transações registradas")
	public Page<TransacaoOutputDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
		return service.listar(paginacao);
		
	}
	
	@PostMapping
	@ApiOperation("Cadastrar nova Transação")
	public ResponseEntity<TransacaoOutputDto> cadastrar(@RequestBody @Valid TransacaoFormDto transacaoFormDto, UriComponentsBuilder uriBuilder) {
		TransacaoOutputDto transacaoOutputDto= service.cadastrar(transacaoFormDto);
		
		URI uri = uriBuilder.path("/transacoes/{id}").buildAndExpand(transacaoOutputDto.getId()).toUri();
		return ResponseEntity.created(uri).body(transacaoOutputDto);
	}
	
	@PutMapping
	@ApiOperation("Atualizar Transação selecionada")
	public ResponseEntity<TransacaoOutputDto> atualizar(@RequestBody @Valid AtulizacaoTransacaoFormDto transacaoFormDto){
		TransacaoOutputDto transacaoOutputDto = service.atualizar(transacaoFormDto);
		
		return ResponseEntity.ok(transacaoOutputDto);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Remover Transacao selecionada")
	public ResponseEntity<TransacaoOutputDto> remover(@PathVariable @NotNull Long id){
		service.remover(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Transação Detalhada")
	public ResponseEntity<TransacaoDetalhadaOutputDto> detalhar(@PathVariable @NotNull Long id){
		TransacaoDetalhadaOutputDto transacaoOutputDto = service.detalhar(id);
		
		return ResponseEntity.ok(transacaoOutputDto);
	}
}
