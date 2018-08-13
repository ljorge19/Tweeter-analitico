package br.com.fiap.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Datas {

	/*
public static String getDataFinal(){
		
	    Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		
		SimpleDateFormat simpleDateFormatFinal = new SimpleDateFormat("yyyy/MM/dd");
		String dataFormatadaFinal = simpleDateFormatFinal.format(dataInicial.getTime());
		
		return dataFormatadaFinal;
		}
	*/
	
	
   public static String getDataInicial(){
	   
	   Calendar dataInicial = Calendar.getInstance();
		
	   dataInicial.add(Calendar.DAY_OF_MONTH, -6);  
		SimpleDateFormat simpleDateFormatInicial = new SimpleDateFormat("yyyy/MM/dd");
		String dataFormatadaInicial = simpleDateFormatInicial.format(dataInicial.getTime());
		
		return dataFormatadaInicial;
		}

}
