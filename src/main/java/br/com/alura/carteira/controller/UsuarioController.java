package br.com.alura.carteira.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.dto.UsuarioOutputDto;
import br.com.alura.carteira.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public Page<UsuarioOutputDto> listar(Pageable paginacao){
		return service.listar(paginacao);
	}
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid UsuarioFormDto usuarioFormDto) {
		service.cadastrar(usuarioFormDto);		
	}

}
