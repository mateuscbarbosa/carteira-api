package br.com.alura.carteira.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDto {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String login;
	
	@NotNull
	@JsonAlias("perfil_id")
	private Long perfilId;
}
