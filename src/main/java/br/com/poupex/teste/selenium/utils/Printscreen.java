package br.com.poupex.teste.selenium.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Printscreen {
	
	private final TakesScreenshot screenshot;	
	
	public Printscreen(WebDriver driver) {
		screenshot = (TakesScreenshot) driver;
	}
	
	public File tirarPrintscreen(String filepath) {
		File screenshotFile = null;
		try {
			screenshotFile = new File(filepath+".png");
			FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), screenshotFile);
			System.out.println("Screenshot salvo em " + screenshotFile.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Não foi possível capturar a imagem: " + e.getMessage());	
		}
		return screenshotFile;
		
	}
	
	public String getPrintAsString() {
        return screenshot.getScreenshotAs(OutputType.BASE64);
	}
}
