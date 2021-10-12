package br.com.alura.carteira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;

class CalculadoraDeImpostoServiceTest {

	@Test
	void transacaoDoTipoCompraNaoDeveriaTerImposto() {
		Transacao transacao = new Transacao(
				1l,
				"XPTO01",
				LocalDate.now(),
				new BigDecimal("30.0"),
				10,
				TipoTransacao.COMPRA,
				new Usuario(1l,"Teste","teste@email.com","123456"));
		
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(BigDecimal.ZERO, imposto);
	}
	
	@Test
	void transacaoDoTipoVendaMenorQueVinteMilNaoDeveriaTerImposto() {
		Transacao transacao = new Transacao(
				1l,
				"XPTO01",
				LocalDate.now(),
				new BigDecimal("30.0"),
				10,
				TipoTransacao.VENDA,
				new Usuario(1l,"Teste","teste@email.com","123456"));
		
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(BigDecimal.ZERO, imposto);
	}
	
	@Test
	void deveriaCalcularImpostoDeTransacaoDoTipoVendaComValorMaiorQueVinteMil() {
		Transacao transacao = new Transacao(
				1l,
				"XPTO01",
				LocalDate.now(),
				new BigDecimal("3000.0"),
				10,
				TipoTransacao.VENDA,
				new Usuario(1l,"Teste","teste@email.com","123456"));
		
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(new BigDecimal("4500.00"), imposto);
	}

}
