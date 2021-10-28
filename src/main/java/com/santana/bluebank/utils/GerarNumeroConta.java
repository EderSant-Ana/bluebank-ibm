package com.santana.bluebank.utils;

import java.util.Random;

public class GerarNumeroConta {
	
	public static String gerarNumeroConta() {
		Random gerador = new Random();
        String accountNumber = "";

        for (int i = 1; i < 6; i++) {
        	accountNumber += gerador.nextInt(10);
        }
        
        accountNumber += "-";       
        String digit = String.valueOf(Math.round(Math.random() * 9));      
        accountNumber += digit;

        return accountNumber;
	}


}
