package br.com.poupex.teste.selenium.utils;

import java.math.BigDecimal;

public abstract class ValoresUtil {
	
	public static BigDecimal stringToBigDecimal(String valor){
		return BigDecimal.valueOf(Double.parseDouble(valor.replace("R$ ","").replaceAll("\\.","").replace(",","."))).setScale(2);
	}

}
