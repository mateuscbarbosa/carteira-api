package br.com.alura.carteira.dto;

import lombok.Setter;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
@Setter
public class AtulizacaoTransacaoFormDto extends TransacaoFormDto{

	@NotNull
	private Long id;
}
