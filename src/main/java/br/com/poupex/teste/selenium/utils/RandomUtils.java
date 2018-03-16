package br.com.poupex.teste.selenium.utils;

import java.util.Random;

public class RandomUtils {
	
	private static Random rd = new Random();
	
	/**
	 * Retorna um número Inteiro aleatório baseado no parâmetro
	 * 
	 * @param base
	 * @return
	 */
	public static int geraIntNaoZero(Integer base) {
		return rd.nextInt(base) + 1;
	}
	
	/**
	 * Gera um número Inteiro aleatório
	 * 
	 * @return
	 */
	public static int geraIntAleatorio() {
		return rd.nextInt();
	}
	
	/**
	 * Gera um inteiro até o valor especificado no parametro
	 * @param limite
	 * @return
	 */
	public static int geraIntAleatorio(int limite) {
		return rd.nextInt(limite);
	}
	
	/**
	 * Gera um double aleatório
	 * @return
	 */
	public static Double geraDoubleAleatorio(){
		return rd.nextDouble();
	}
}
