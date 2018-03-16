package br.com.poupex.teste.selenium.elementos;

import org.openqa.selenium.By;

import br.com.poupex.teste.selenium.utils.EsperaUtil;

public class Button extends Componentes<Button> {

	public Button(By by) {
		super(by);
	}
	
	public Button(String id) {
		super(id);
	}

	/**
	 * Clica no elemento.
	 */
	public Button clica() {
		EsperaUtil.esperaElementoClicavel(driver, by);
		driver.findElement(by).click();
		return this;
	}

	/**
	 * Clica no elemento quantas vezes forem especificadas no parâmetro, a cada 1 segundo.
	 * 
	 */
	public Button clica(int vezes) {
		int i = 0;
		while(i++ <vezes){
			driver.findElement(by).click();
			EsperaUtil.espere(1);
		}
		return this;
	}

}
