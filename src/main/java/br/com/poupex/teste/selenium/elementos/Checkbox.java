package br.com.poupex.teste.selenium.elementos;

import org.openqa.selenium.By;

public class Checkbox extends Componentes<Checkbox>{
	
	public Checkbox(By by) {
		super(by);
	}
	
	public Checkbox(String id) {
		super(By.id(id));
	}

	public Checkbox marcar() {
		if (driver.findElement(by).isSelected() == false) {
			driver.findElement(by).click();
		}
		return this;
	}
	
	public Checkbox desmarcar() {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
		return this;
	}

}
