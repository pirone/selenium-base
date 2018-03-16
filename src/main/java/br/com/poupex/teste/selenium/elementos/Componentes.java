package br.com.poupex.teste.selenium.elementos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import br.com.poupex.teste.selenium.config.SeleniumWebDriver;

public abstract class Componentes<T> {
	
	protected By by;
	protected WebDriver driver;

	public Componentes(By by) {
		this.by = by;
		driver = SeleniumWebDriver.getDriverFor();
	}
	
	public Componentes(String id) {
		this.by = By.id(id);
		driver = SeleniumWebDriver.getDriverFor();
	}
	
	
	@SuppressWarnings("unchecked")
	public T habilitado() {
		Assert.assertTrue(driver.findElement(by).isEnabled());
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public T visivel() {
		Assert.assertTrue(driver.findElement(by).isDisplayed());
		return (T) this;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public By getBy() {
		return by;
	}
	
	protected String getStringId() {
		String idValidacaoInput = by.toString().replace("InputDate", "").replace("By.id: ", "").replace("By.cssSelector: ", "").replace("[id$=", "").replace("]", "");
		return idValidacaoInput;
	}
	
	public WebElement getWebElement() {
		return driver.findElement(by);
	}
	
}
