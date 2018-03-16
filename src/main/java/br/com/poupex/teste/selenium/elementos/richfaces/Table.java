package br.com.poupex.teste.selenium.elementos.richfaces;

import org.openqa.selenium.By;

import br.com.poupex.teste.selenium.elementos.Componentes;
import br.com.poupex.teste.selenium.utils.EsperaUtil;

public class Table extends Componentes<Table> {
	
	private String id;
	
	public Table(String id) {
		super(id);
		this.id = id;
	}
	
	public void editaPrimeiroResultado() {
		clicaBotaoAcao(0, "a", "botaoEditar");
	}
	
	public void editaPrimeiroResultado(String tagName) {
		clicaBotaoAcao(0, tagName, "botaoEditar");
	}
	
	public void detalhaPrimeiroResultado() {
		clicaBotaoAcao(0, "a", "botaoDetalhar");
	}
	
	public void detalhaPrimeiroResultado(String tagName) {
		clicaBotaoAcao(0, tagName, "botaoDetalhar");
	}
	
	public void excluiPrimeiroResultado() {
		clicaBotaoAcao(0, "a", "botaoExcluir");
	}
	
	public void excluiPrimeiroResultado(String tagName) {
		clicaBotaoAcao(0, tagName, "botaoExcluir");
	}
	
	public void clicaBotaoAcao(int linha, String tagName ,String classBotao) {
		driver.findElement(By.cssSelector("tr[id*='"+id+":"+linha+"'] "+tagName+"[class='"+classBotao+"']")).click();
		EsperaUtil.espere(1);
	}
	
	public String recuperaTexto(int linha, int coluna) {
		return driver.findElements(By.cssSelector("tr[id*='"+id+":"+linha+"'] td")).get(coluna).getText();
	}
	
	public int getRowCount() {
		return (driver.findElements(By.cssSelector("tr[id*='"+id+"']")).size())-1;
	}
}


