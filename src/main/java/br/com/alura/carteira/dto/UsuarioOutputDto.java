package br.com.alura.carteira.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutputDto {
	
	private Long id;
	private String nome;
	private String login;
}
