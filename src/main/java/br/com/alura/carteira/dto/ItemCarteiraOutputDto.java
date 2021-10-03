package br.com.alura.carteira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemCarteiraOutputDto {

	private String ticker;
	private Long quantidade;
	private Double percentual;
}
