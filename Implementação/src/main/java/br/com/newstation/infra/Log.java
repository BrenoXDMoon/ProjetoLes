package br.com.newstation.infra;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Log {


	@SuppressWarnings("null")
	public static void  salvar(String operacao, String nivel){
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");  
        String strDate = dateFormat.format(date);  
	    FileWriter arq = null;
		try {
			arq = new FileWriter("C:\\log.txt", true);
			arq.write("Operação: "+ operacao+ " Data: " + strDate + " - " + nivel+ '\n');
			arq.close();
			
		} catch (IOException e1) {
			System.out.println("arquivo existir não");
		}

	    
	}

}
