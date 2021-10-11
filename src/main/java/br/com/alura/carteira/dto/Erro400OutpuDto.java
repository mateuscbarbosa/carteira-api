package br.com.alura.carteira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro400OutpuDto {

	private String campo;
	private String mensagem;
}
