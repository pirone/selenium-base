package br.com.poupex.teste.selenium.elementos.richfaces;

import org.openqa.selenium.By;

import br.com.poupex.teste.selenium.elementos.Componentes;

public class Picklist extends Componentes<Picklist>{

	private String id;

	public Picklist(String id) {
		super(id);
		this.id = id;
	}
	
	public Picklist selecionaItem(Integer item) {
		driver.findElement(By.name(id+"Item"+item)).click();
		return this;
	}
	
	public Picklist selecionaItem(String texto) {
		driver.findElement(By.xpath("//div[contains(text(), '"+texto+"')]")).click();
		return this;
	}
	
	public Picklist addTodos() {
		driver.findElement(By.name(id+"addAll")).click();
		return this;
	}
	
	public Picklist addSelecionado() {
		driver.findElement(By.name(id+"add")).click();
	
		return this;
	}
	public Picklist removerTodos() {
		driver.findElement(By.name(id+"removeAll")).click();
		return this;
	}
	
	public Picklist RemoverSelecionado() {
		driver.findElement(By.name(id+"remove")).click();
		return this;
	}
	

}
