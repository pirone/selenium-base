package br.com.poupex.teste.selenium.utils;

import java.util.ArrayList;
import java.util.Random;

public class GeradorNome {
	
private ArrayList<String> nome = new ArrayList<String>();

   private String[] Beginning = { "Ba", "Ca", "Ra", "Ta", "Be",
         "Ce", "To", "Ge", "Ga", "Pa", "To", "Pe", "Lu", "Re",
         "We", "Er", "Lo", "An", "Bi", "Ma", "Da", "Rei", "Fi",
         "Mar", "Le", "Ul","Bár" };
   private String[] Middle = { "cas", "dro", "na", "ley", "fa", "el",
         "mi", "re", "la", "ri", "no", "de", "ria", "ção", "do",
         "da", "de", "di", "po", "ta", "tro","é", "õ" };
   private String[] End = { "r", "dro", "cas", "do", "to", "er", "der",
         "no", "ra", "des", "la", "na", "no", "n", "ér", "ço" };
   
   private Random rand = new Random();

   public String gerarNome(int qtd) {
	   
	   nome.clear();

	   int i=1;
	   
	   do{
		   nome.add(Beginning[rand.nextInt(Beginning.length)] + 
		            Middle[rand.nextInt(Middle.length)]+
		            End[rand.nextInt(End.length)]);
	   } while(i++<qtd);
	
      return nome.toString().replace(",", "").replace("[", "").replace("]", "");
	}
}
