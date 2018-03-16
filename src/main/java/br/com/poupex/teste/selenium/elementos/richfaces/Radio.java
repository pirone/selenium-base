package br.com.poupex.teste.selenium.elementos.richfaces;

import org.openqa.selenium.By;

import br.com.poupex.teste.selenium.elementos.Componentes;

public class Radio extends Componentes<Radio> {

	private String id;

	public Radio(String id) {
		super(id);
		this.id = id;
	}
	
	public Radio selecionaNao() {
		driver.findElement(By.id(id+":1")).click();
		return this;
	}
	
	public Radio selecionaSim() {
		driver.findElement(By.id(id+":0")).click();
		return this;
	}
	
	public Radio selecionaSegundo() {
		driver.findElement(By.id(id+":1")).click();
		return this;
	}
	
	public Radio selecionaPrimeiro() {
		driver.findElement(By.id(id+":0")).click();
		return this;
	}
}