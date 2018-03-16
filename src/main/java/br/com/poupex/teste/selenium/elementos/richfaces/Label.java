package br.com.poupex.teste.selenium.elementos.richfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.poupex.teste.selenium.config.SeleniumWebDriver;

public class Label {
	
	private String titulo;
	private WebDriver driver;

	public Label(String titulo) {
		this.titulo = titulo;
		driver = SeleniumWebDriver.getDriverFor();
	}

	public String recuperaValor() {
		return driver.findElement(By.xpath("//label[.='"+titulo+"']/following-sibling::label")).getText();
	}
	
	
	/**
	 * @param Caso o selenium localize mais de um elemento com a mesma label, informar a posição como parâmetro
	 * @return
	 */
	public String recuperaValor(int posicao) {
		List<WebElement> locateds = driver.findElements(By.xpath("//label[.='"+titulo+"']/following-sibling::label"));
		return locateds.get(posicao).getText();
	}

}
