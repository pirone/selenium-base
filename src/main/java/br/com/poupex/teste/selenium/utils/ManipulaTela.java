package br.com.poupex.teste.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import br.com.poupex.teste.selenium.config.SeleniumWebDriver;

public abstract class ManipulaTela {
	
	private static WebDriver driver = SeleniumWebDriver.getDriverFor();
	private static JavascriptExecutor js = (JavascriptExecutor) driver;
	
	/**
	 * Pause até a modal de "Processando" desaparecer
	 */
	public static void esperaPopupProcessamento() {
		EsperaUtil.esperaElementoInvisivel(driver, By.id("popupAjaxStatus"));
	}
	
	
	/**
	 * Abaixa tela sempre na vertical
	 * @param valorY
	 */
	public static void abaixaTela(int valorY){
		js.executeScript("window.scrollBy(0,"+valorY+")", "");
	}
	
	
	/**
	 * Valor X = Horizontal
	 * Valor Y = Vertical
	 * @param valorX
	 * @param valorY
	 */
	public static void abaixaTela(int valorX,int valorY){
		js.executeScript("window.scrollBy("+valorX+","+valorY+")", "");
	}

}
