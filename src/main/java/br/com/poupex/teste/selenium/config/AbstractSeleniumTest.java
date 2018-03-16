package br.com.poupex.teste.selenium.config;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.relevantcodes.extentreports.ExtentTest;

import br.com.poupex.teste.selenium.config.SeleniumWebDriver.Browser;
import br.com.poupex.teste.selenium.utils.AmbienteUtil;
import br.com.poupex.teste.selenium.utils.AmbienteUtil.Ambiente;
import br.com.poupex.teste.selenium.utils.EsperaUtil;
import br.com.poupex.teste.selenium.utils.Printscreen;

@Test
public abstract class AbstractSeleniumTest {

	protected static final String OutputDir = System.getProperty("user.dir");
	private static final String EvidenciaOutputDir = OutputDir + "/evidencias";

	protected static Printscreen printscreen;
	protected static JavascriptExecutor js;
	protected static Faker faker;
	protected ExtentTest test;
	protected static WebDriver driver = driverExecucao();
	
	protected static void setDriver(WebDriver driver) {
		AbstractSeleniumTest.driver = driver;
	}

	protected String userLogin() {
		return "64054691315";
	}
	
	/**
	 * Define o driver padrão para execução de todos os testes.
	 * @return
	 */
	public static WebDriver driverExecucao() {
		return SeleniumWebDriver.getDriverFor(Browser.IE);
	}

	@BeforeSuite
    public void beforeSuite() {
		printscreen = new Printscreen(driver);
		js = (JavascriptExecutor) driver;
		faker = new Faker();
		driver.manage().window().maximize();
		logar();
    }
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get(getAmbienteUrl() + urlTest());
	}

	public abstract String urlTest();
	
	
	
	protected void logar() {
		if(getAmbienteUrl() == AmbienteUtil.Ambiente.LOCAL.getUrl()) {
			driver.get(AmbienteUtil.Ambiente.DESENV.getUrl());
			driver.findElement(By.id("username")).sendKeys(userLogin());
			driver.findElement(By.id("username")).submit();
		} else	{
		driver.get(getAmbienteUrl()+"poupex-idp/");
		driver.findElement(By.id("username")).sendKeys(userLogin());
		driver.findElement(By.id("username")).submit();
		}
	}
	
	@AfterMethod
    protected void afterMethod(ITestResult result) {
		printscreen.tirarPrintscreen(EvidenciaOutputDir+"/"+result.getMethod().getMethodName());
    }
	
	@AfterSuite
    protected void afterSuite() {
		try {
			driver.quit();
		} catch (Throwable e) {
			System.out.println("Erro ao encerrar o WebDriver." + e.getMessage());
		}       
    }
	
	/**
	 * @param id
	 * @return
	 */
	protected WebElement byId(String id) {
		return driver.findElement(By.id(geraIdComponente(id)));
	}
	
	public static String getAmbienteUrl(){
		return Ambiente.TESTE.getUrl();
	}
	
	private String geraIdComponente(String id) {
		return id + ":" + id;
	}
	/**
	 * Pause até a modal de "Processando" desaparecer
	 */
	protected void esperaPopupProcessamento() {
		EsperaUtil.esperaElementoInvisivel(driver, By.id("popupAjaxStatus"));
	}
	
	
	/**
	 * Abaixa tela sempre na vertical
	 * @param valorY
	 */
	protected void abaixaTela(int valorY){
		js.executeScript("window.scrollBy(0,"+valorY+")", "");
	}
	
	
	/**
	 * Valor X = Horizontal
	 * Valor Y = Vertical
	 * @param valorX
	 * @param valorY
	 */
	protected void abaixaTela(int valorX,int valorY){
		js.executeScript("window.scrollBy("+valorX+","+valorY+")", "");
	}
	
}
