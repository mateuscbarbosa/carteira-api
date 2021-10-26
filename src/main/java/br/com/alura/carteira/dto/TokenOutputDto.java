package br.com.alura.carteira.dto;

import lombok.Getter;

@Getter
public class TokenOutputDto {
	
	private String token;
	
	public TokenOutputDto(String token) {
		this.token=token;
	}
	
}
