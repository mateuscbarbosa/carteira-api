package br.com.alura.carteira.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.alura.carteira.dto.AtualizacaoUsuarioFormDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.dto.UsuarioOutputDto;
import br.com.alura.carteira.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	@ApiOperation("Listar Usuários")
	public Page<UsuarioOutputDto> listar(Pageable paginacao){
		return service.listar(paginacao);
	}
	
	@PostMapping
	@ApiOperation("Cadastrar novo Usuário")
	public ResponseEntity<UsuarioOutputDto> cadastrar(@RequestBody @Valid UsuarioFormDto usuarioFormDto, UriComponentsBuilder uriBuilder) {
		UsuarioOutputDto usuarioOutputDto = service.cadastrar(usuarioFormDto);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioOutputDto.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioOutputDto);
	}
	
	@PutMapping
	@ApiOperation("Atualizar Usuário selecionado")
	public ResponseEntity<UsuarioOutputDto> atualizar(@RequestBody @Valid AtualizacaoUsuarioFormDto usuarioFormDto){
		UsuarioOutputDto usuarioOutputDto = service.atualizar(usuarioFormDto);
		
		return ResponseEntity.ok(usuarioOutputDto);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Remover Usuário selecionado")
	public ResponseEntity<UsuarioOutputDto> remover(@PathVariable @NotNull Long id){
		service.remover(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Usuário Detalhado")
	public ResponseEntity<UsuarioOutputDto> detalhar(@PathVariable @NotNull Long id){
		UsuarioOutputDto usuarioOutputDto = service.detalhar(id);
		
		return ResponseEntity.ok(usuarioOutputDto);
	}

}
