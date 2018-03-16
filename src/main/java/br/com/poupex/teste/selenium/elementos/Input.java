package br.com.poupex.teste.selenium.elementos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import br.com.poupex.teste.selenium.utils.EsperaUtil;
import br.com.poupex.teste.selenium.utils.Formatter;

public class Input extends Componentes<Input> {

	public Input(By by) {
	super(by);
	}
	
	public Input(String id) {
		super(id);
	}
	
	public Input digita(String texto) {
		EsperaUtil.esperaElementoClicavel(driver, by);
		findBy().click();
		findBy().clear();
		EsperaUtil.espere(1);
		findBy().sendKeys(texto);
		return this;
	}
	
	public Input verificaValor(String texto, String mascara) {
		String textoCampo = findBy().getAttribute("value");
		Assert.assertEquals(textoCampo, Formatter.formatar(texto, mascara), "Valor do campo " 
			+ by + " diferente. Valor atual: " + textoCampo);
		return this;
	}
	
	public Input verificaValor(String mascara) {
		String textoCampo = findBy().getAttribute("value");
		Assert.assertEquals(textoCampo, Formatter.formatar(textoCampo, mascara), "Valor do campo " 
			+ by + " diferente. Valor atual: " + textoCampo);
		return this;
	}
	
	public String recuperaTexto() {
		return findBy().getAttribute("value");
	}
	
	public Input sendKeys(String texto) {
		findBy().sendKeys(texto);
		return this;
	}
	
	private WebElement findBy() {
		return driver.findElement(by);
	}
	
	public Input limpa() {
		findBy().clear();
		return this;
	}
	
	public Input submeter() {
		findBy().submit();
		return this;
	}
	
}
