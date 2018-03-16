package br.com.poupex.teste.selenium.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MaskFormatter;

public class GeradorCPF {
	
	private static int geraNumAleatorio(){
		return (int) (Math.random()*10);
	}
	
	private static List<Integer> geraCPFParcial(){
		List<Integer> cpfParcial = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++){
			cpfParcial.add(geraNumAleatorio());
		}
		return cpfParcial;
	}
	
	private static List<Integer> geraPrimeiroDigito(List<Integer> cpfParcial){
		List<Integer> listaNumMultiplicados = new ArrayList<>();
		
		int primeiroDigito;
		int totalSomatoria = 0;
		int restoDivisao;
		int peso = 10;
		
		for(int item : cpfParcial){
			listaNumMultiplicados.add(item * peso);
			peso--;
		}
		
		for(int item : listaNumMultiplicados){
			totalSomatoria += item;
		}
		
		restoDivisao = (totalSomatoria % 11);
		
		if (restoDivisao<2){
			primeiroDigito = 0;
		} else {
			primeiroDigito = 11 - restoDivisao;
		}
		
		cpfParcial.add(primeiroDigito);
		return cpfParcial;
			
	}
	
	private static List<Integer> geraSegundoDigito(List<Integer> cpfParcial){
		List<Integer> listaNumMultiplicados = new ArrayList<>();
		
		int segundoDigito;
		int totalSomatoria = 0;
		int restoDivisao;
		int peso = 11;
		
		for(int item: cpfParcial){
			listaNumMultiplicados.add(item * peso);
			peso--;
		}
		
		for(int item : listaNumMultiplicados){
			totalSomatoria += item;
		}
		
		restoDivisao = (totalSomatoria % 11);
		
		if (restoDivisao<2){
			segundoDigito = 0;
		} else{
			segundoDigito = 11 - restoDivisao;
		}
		
		cpfParcial.add(segundoDigito);
		return cpfParcial;
	}
	
	public static String gerarCPFsemMascara(){
		return StringUtils.join("", geraSegundoDigito(geraPrimeiroDigito(geraCPFParcial())).toArray());
	}
	
	public static String gerarCPFcomMascara(){
		try{
			MaskFormatter mf = new MaskFormatter("###.###.###-##");
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(gerarCPFsemMascara());
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
