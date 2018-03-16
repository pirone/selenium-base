package br.com.poupex.teste.selenium.config;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class SeleniumWebDriver {
	
	protected static WebDriver webDriver;

	private static final HashMap<String, String[]> drivers = new HashMap<>();
	static {	
		drivers.put("ie", new String[] { "webdriver.ie.driver", System.getenv("IEDRIVER") });
		drivers.put("chrome", new String[] { "webdriver.chrome.driver", System.getenv("CHROMEDRIVER") });
		drivers.put("firefox", new String[] { "webdriver.firefox.bin", System.getenv("FIREFOXBIN") });
	}
	
	public static WebDriver getDriverFor(Browser browser) {
		if (webDriver == null) {
			switch (browser) {
			case IE:
				webDriver = getIEDriver();
				return webDriver;
			case CHROME:
				webDriver = getChromeDriver();
				return webDriver;
			case FIREFOX:
				webDriver = getFirefoxDriver();
				return webDriver;
			}
		}
		return webDriver;
	}

	public static WebDriver getDriverFor() {
		return AbstractSeleniumTest.driver;
	}

	private static void setSystemProperty(String[] driverInfo) {
		System.setProperty(driverInfo[0], new File(driverInfo[1]).getAbsolutePath());
	}

	private static InternetExplorerDriver getIEDriver() {
		setSystemProperty(drivers.get("ie"));
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		options.setCapability("requireWindowFocus", true);
		return new InternetExplorerDriver(options);
	}

	private static ChromeDriver getChromeDriver() {
		setSystemProperty(drivers.get("chrome"));
		return new ChromeDriver();
	}

	private static FirefoxDriver getFirefoxDriver() {
		setSystemProperty(drivers.get("firefox"));
		System.setProperty("webdriver.gecko.driver", "D:/Pedro Henrique/SVN Poupex - Teste/Teste/Selenium/geckodriver.exe");
		
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("marionette", true);
		return new FirefoxDriver(options);
	}
	
	public static enum Browser {
		IE, CHROME, FIREFOX;
	}

}
