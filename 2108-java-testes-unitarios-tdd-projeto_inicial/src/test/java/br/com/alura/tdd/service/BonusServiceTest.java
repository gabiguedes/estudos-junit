package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {

	@Test
	void bonusDeverisSerZeroParaFuncionarioComSalarioMuitoAlto() {
		BonusService service = new BonusService();
		
		//primeira opção para trabalhar com exceptions
		//assertThrows(IllegalArgumentException.class, () -> service.calcularBonus(new Funcionario("Sergio", LocalDate.now(), new BigDecimal(25000))));
		
		//outra maneira de testar exceptions
		try {
			service.calcularBonus(new Funcionario("Sergio", LocalDate.now(), new BigDecimal(25000)));
			fail("Não deu a exception!");
		} catch (Exception e) {
			assertEquals("Funcionário com salário maior do que R$ 1000,00 não pode receber bônus!", e.getMessage());
		}
		
	}
	
	@Test
	void bonusDeverisSer10PorCentoDoSalario() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Sergio", LocalDate.now(), new BigDecimal(2500)));
		
		assertEquals(new BigDecimal("250.00"), bonus);
	}
	
	@Test
	void bonusDeveriaSerDezPorCentoParaSalarioDeExatamente10000() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Sergio", LocalDate.now(), new BigDecimal(10000)));
		
		assertEquals(new BigDecimal("1000.00"), bonus);
	}

}
