package br.com.newstation.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

public class CalendarConverter{

	private DateTimeConverter converter = new DateTimeConverter();
	
	
	public CalendarConverter() {
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

	public Calendar converte(String dataTxt) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = sdf.parse(dataTxt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		return cal;
	}
}
