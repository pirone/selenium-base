package br.com.poupex.teste.selenium.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.github.javafaker.Faker;

public abstract class DataUtil {
	
	private final static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private final static SimpleDateFormat dateFormatterNoMask = new SimpleDateFormat("ddMMyyyy");
	
	static Faker faker = new Faker();
	
	public static enum Calendario {
		DIA(5), MES(2), ANO(1);
		
		private int tipo;
		
		Calendario(int tipo) {
			this.tipo = tipo;
		}
		
		public int getTipo(){
			return tipo;
		}
	}
	
	public static String getHoje(Mascara mascara){
		Date hoje = new Date();
		return selecionaComOuSemMascara(hoje, mascara);
	}
	
	public static String getFormattedModifiedDate(Calendario calendario, int qtd, Mascara mascara){
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendario.getTipo(), qtd);
		return selecionaComOuSemMascara(calendar.getTime(), mascara);
	}
	
	public static Date parseDate(Integer dia, Integer mes, Integer ano){		
		Calendar calendario = new Calendar.Builder()
			.setDate(ano, mes, dia)
			.build();	
		return calendario.getTime();	
	}
	
	public static String geraDtNascAleatoria(Mascara mascara) {
		Date dataGerada = parseDate(faker.number().numberBetween(1, 28), faker.number().numberBetween(0, 11), faker.number().numberBetween(1940, 2000));
		return selecionaComOuSemMascara(dataGerada, mascara);
	}
	

	private static String selecionaComOuSemMascara(Date data, Mascara mascara) {
		switch (mascara) {
		case TRUE:
			return dateFormatter.format(data);
		case FALSE:
			return dateFormatterNoMask.format(data);
		default:
			return dateFormatter.format(data);
		}
	}
	
	public enum Mascara {
		TRUE,
		FALSE;
	}
	

}
