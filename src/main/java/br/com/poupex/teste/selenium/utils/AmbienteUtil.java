package br.com.poupex.teste.selenium.utils;

import java.net.UnknownHostException;

public abstract class AmbienteUtil {
	
	public static String maquina = "codti17128";
	
	public static String getMaquina() {
		return maquina;
	}

	public static void setMaquina(String maquina) {
		AmbienteUtil.maquina = maquina;
	}

	public enum Ambiente {
		
		TESTE("http://testepexnet.poupex.com.br/"), 
		HOMOLOG("http://homologapexnet.poupex.com.br/"),
		DESENV("http://desenvpexnet.poupex.com.br/"),
		LOCAL(getMachineName() +".poupex.com.br:8080/"),
		PRODUCAO("https://www.fhe.org.br/");
		
		private String url;
		
		Ambiente(String url){
			this.url = url;
		}
		
		public String getUrl(){
			return url;
		}
		
		public static String getMachineName() {
			try {
				maquina = java.net.InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return maquina;
		}
	}
}

