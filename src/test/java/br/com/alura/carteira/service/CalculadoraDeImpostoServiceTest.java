package br.com.alura.carteira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;

class CalculadoraDeImpostoServiceTest {

	private CalculadoraDeImpostoService calculadora;

	private Transacao criarTransacao(BigDecimal preco, Integer quantidade, TipoTransacao tipo) {
		Transacao transacao = new Transacao(
				1l,
				"XPTO01",
				LocalDate.now(),
				preco,
				quantidade,
				tipo,
				new Usuario(1l,"Teste","teste@email.com","123456"));
		return transacao;
	}
	
	@BeforeEach
	private void inicializarCalculadora() {
		calculadora = new CalculadoraDeImpostoService();
	}
	
	@Test
	void transacaoDoTipoCompraNaoDeveriaTerImposto() {
		Transacao transacao = criarTransacao(new BigDecimal("30"),10,TipoTransacao.COMPRA);
		
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(BigDecimal.ZERO, imposto);
	}
	
	@Test
	void transacaoDoTipoVendaMenorQueVinteMilNaoDeveriaTerImposto() {
		Transacao transacao = criarTransacao(new BigDecimal("30"),10,TipoTransacao.VENDA);
			
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(BigDecimal.ZERO, imposto);
	}
	
	@Test
	void deveriaCalcularImpostoDeTransacaoDoTipoVendaComValorMaiorQueVinteMil() {
		Transacao transacao = criarTransacao(new BigDecimal("3000"),10,TipoTransacao.VENDA);
				
		BigDecimal imposto = calculadora.calcular(transacao);
		
		assertEquals(new BigDecimal("4500.00"), imposto);
	}

}
