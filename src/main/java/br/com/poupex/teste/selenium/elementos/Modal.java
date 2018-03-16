package br.com.poupex.teste.selenium.elementos;

public class Modal extends Componentes<Modal> {

	protected String id;

	public Modal(String id) {
		super(id);
		this.id = id;
	}
	
	public String recuperaTexto() {
		return driver.findElement(by).getText();
	}
	
	public String recuperaId(){
		return id;
	}
	


}
