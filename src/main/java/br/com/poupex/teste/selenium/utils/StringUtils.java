package br.com.poupex.teste.selenium.utils;

public class StringUtils {
	
	public static char REPEATING_CHAR = 'Ç';
	
	public static String strRepeat(int length) {
		return strRepeat(length, "", REPEATING_CHAR);
	}
	
	public static String join(String glue, Object... elems) {
		String joined = "";
		for (Object el : elems) {
			joined += el;
		}
		return joined;
	}
	
	public static String strRepeat(int length, String startingText) {
		return strRepeat(length, startingText, REPEATING_CHAR);
	}
	
	public static String strRepeat(int length, String startingText, char repeatingText) {
		StringBuffer sb = new StringBuffer(startingText);
		for (int i = 0; i < (length - startingText.length()); i++) {
			sb.append(repeatingText);
		}
		return sb.toString();
	}

}
