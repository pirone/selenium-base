package br.com.poupex.teste.selenium.elementos.richfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import br.com.poupex.teste.selenium.elementos.Componentes;
import br.com.poupex.teste.selenium.utils.EsperaUtil;

public class Combo extends Componentes<Combo> {

	public Combo(By by) {
		super(by);
	}
	
	public Combo(String id) {
		super(id);
	}
	
	public Combo seleciona(String texto) {
		new Select(driver.findElement(by)).selectByVisibleText(texto);
		return this;
	}

	public Combo seleciona(Integer posicao) {
		EsperaUtil.esperaElementoClicavel(driver, by);
		new Select(driver.findElement(by)).selectByIndex(posicao);
		return this;
	}

	public String recuperaValorSelecionado() {
		return new Select(driver.findElement(by)).getFirstSelectedOption().getText();
	}

}
