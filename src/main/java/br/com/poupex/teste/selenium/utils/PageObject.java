package br.com.poupex.teste.selenium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

import br.com.poupex.teste.selenium.config.SeleniumWebDriver;

public abstract class PageObject {
	
	protected WebDriver driver = SeleniumWebDriver.getDriverFor();
	protected Faker faker = new Faker();
	protected GeradorCPF geradorCPF = new GeradorCPF();
	protected JavascriptExecutor js = (JavascriptExecutor) driver;
	
}
