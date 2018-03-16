package br.com.poupex.teste.selenium.config;

import java.io.File;

import br.eti.kinoshita.testlinkjavaapi.model.Execution;

public class TestLinkTO {
	
	public final String TESTLINK_KEY = "0dffe9959f8407203e7d566efdbabca0";
	public final String TESTLINK_URL = "http://testelinkh01/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
	
	private Integer planoDeTesteId = 10873;
	private Integer buildId = 123;
	
	private Integer casoDeTesteId;
	private File evidencia;
	private Execution ultimaExecucao;
	
	public TestLinkTO() {
		
	}
	
	/**
	 * @param planoDeTesteId - Id do plano de teste existente no TestLink
	 * @param buildId - Id da build/release que será registrado o teste
	 */
	public TestLinkTO(Integer planoDeTesteId, Integer buildId) {
		this.planoDeTesteId = planoDeTesteId;
		this.buildId = buildId;
	}
	
	public Integer getPlanoDeTesteId() {
		return planoDeTesteId;
	}
	public void setPlanoDeTesteId(Integer planoDeTesteId) {
		this.planoDeTesteId = planoDeTesteId;
	}
	public Integer getBuildId() {
		return buildId;
	}
	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}
	public Integer getCasoDeTesteId() {
		return casoDeTesteId;
	}
	public void setCasoDeTesteId(Integer casoDeTesteId) {
		this.casoDeTesteId = casoDeTesteId;
	}

	public File getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(File evidencia) {
		this.evidencia = evidencia;
	}
	
	public Execution getUltimaExecucao() {
		return this.ultimaExecucao;
	}

	public void setUltimaExecucao(Execution ultimaExecucao) {
		this.ultimaExecucao = ultimaExecucao;
	}

}
