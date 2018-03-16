package br.com.poupex.teste.selenium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import br.com.poupex.teste.selenium.config.AbstractSeleniumTestLink;
import br.com.poupex.teste.selenium.utils.EsperaUtil;

public class TesteExemplo extends AbstractSeleniumTestLink {
	
	@Test
	public void testeTest() throws Exception {
		try {
			EsperaUtil.esperaElementoClicavel(driver, By.cssSelector("[id*='menuEmpreendimento']"));
			driver.findElement(By.cssSelector("[id*='menuEmpreendimento']")).click();
	        registraSucesso();
		} catch (Exception e) {
			registraFalhaComMensagem(e);
		} catch (AssertionError e) {
			registraFalhaComMensagem(e);
		}
	}
	
	@Override
	public Integer testCaseId() {
		return 10875;
	}

	@Override
	public String urlTest() {
		//super.setTestLinkTO(new TestLinkTO(111111, 476545)); //Informa ID do plano de teste e build a serem atualziados no testlink
		return "sci/";
	}

}
