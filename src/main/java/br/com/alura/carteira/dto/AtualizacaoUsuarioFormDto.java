package br.com.alura.carteira.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoUsuarioFormDto extends UsuarioFormDto{
	
	@NotNull
	private Long id;
	
	@NotBlank
	private String senha;
}
