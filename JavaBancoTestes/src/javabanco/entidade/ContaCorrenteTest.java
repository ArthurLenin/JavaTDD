package javabanco.entidade;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ContaCorrenteTest {

	@Test
	public void testSaldo() {
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		assertEquals(0, cc.saldo(), 0);
	}

	@Test
	public void testCredito() {
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		cc.credito(100);
		assertEquals(100, cc.saldo(), 0);
	}

	@Test
	public void testDebito() {
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		cc.debito(100);
		assertEquals(-100, cc.saldo(), 0);
	}

	@Test
	public void testExtrato() {
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		cc.credito(100);
		cc.debito(100);
		ArrayList<Operacao> listaOperacoes = cc.extrato();
		assertEquals(2, listaOperacoes.size());
		assertEquals(100, listaOperacoes.get(0).getValor(), 0);
		assertEquals("CREDITO",listaOperacoes.get(0).getTipoOperacao());
		assertEquals(100, listaOperacoes.get(1).getValor(), 0);
		assertEquals("DEBITO", listaOperacoes.get(1).getTipoOperacao());
	}
	
	@Test
	public void testSaldoPequenosFloats() {
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		cc.credito(0.1f);
		cc.credito(0.2f);
		assertEquals(0.3f, cc.saldo(), 0.0f);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreditoValorNegativo() {
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		cc.credito(-10);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDebitoValorNegativo() {
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		cc.debito(-10);		
	}

	@Test
	public void testGetTitular(){
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		assertEquals ("jessica", cc.getTitular());	
	}
	
	@Test
	public void testGetNumero(){
		ContaCorrente cc = new ContaCorrente(1221,"jessica");
		assertEquals(1221, cc.getNumero());	
	}
	
	@Test
	public void testTransferencia(){
		ContaCorrente ccOrigem = new ContaCorrente(1221,"jessica");
		ContaCorrente ccDestino = new ContaCorrente (8586, "vitor");
		ccOrigem.tranferencia(100, ccDestino);
		assertEquals(-100, ccOrigem.saldo(),0);
		assertEquals(100,  ccDestino.saldo(),0);	
	}
		
	
}
