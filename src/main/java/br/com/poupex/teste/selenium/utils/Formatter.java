package br.com.poupex.teste.selenium.utils;

import br.com.poupex.commons.infra.util.helper.Formatador;

public final class Formatter {

	private static final int TAMANHO_CPF = 11;

	private Formatter() {
	}

	/*
	 * realiza a formatação do valor de acordo com a mascara enviada
	 */
	public static String formatar(final String valor, final String mascara) {

		final StringBuilder dado = new StringBuilder("");
		// remove caracteres nao numericos
		for (int i = 0; i < valor.length(); i++) {
			final char c = valor.charAt(i);
			if (Character.isDigit(c)) {
				dado.append(c);
			}
		}

		int indMascara = mascara.length();
		int indCampo = dado.length();

		for (; indCampo > 0 && indMascara > 0;) {
			if (mascara.charAt(--indMascara) == '#') {
				indCampo--;
			}
		}

		final StringBuilder saida = new StringBuilder("");
		if(mascara.charAt(0) != '#'){
			saida.append(mascara.charAt(0));
		}
		for (; indMascara < mascara.length(); indMascara++) {
			saida.append(mascara.charAt(indMascara) == '#' ? dado.charAt(indCampo++) : mascara.charAt(indMascara));
		}
		return saida.toString();
	}

	public static String formatarCpf(final String cpf) {
		String cpfjAux = cpf;
		while (cpfjAux.length() < 11) {
			cpfjAux = "0".concat(cpfjAux);
		}
		return formatar(cpfjAux, "###.###.###-##");
	}

	public static String formatarCnpj(final String cnpj) {
		String cnpjAux = cnpj;
		while (cnpjAux.length() < 14) {
			cnpjAux = "0".concat(cnpjAux);
		}
		return formatar(cnpjAux, "##.###.###/####-##");
	}

	public static String formatarCep(final String cep) {
		return formatar(cep, "##.###-###");
	}

	public static String preencherCpfComZero(String cpf) {
		final Formatador formatador = new Formatador();
		return formatador.preencheZeros(cpf, TAMANHO_CPF);
	}
}
