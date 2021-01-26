package br.com.terrenobenzido.controle.converters;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Calendar.class )
public class CalendarConverter implements Converter {

	private DateTimeConverter converter = new DateTimeConverter();
	
	
	public CalendarConverter() {
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}
	//da tela pro backend
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataTxt) {
		
		Date data = (Date) converter.getAsObject(context, component, dataTxt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		
		return calendar;
	}

	
	//do backend para a tela
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object dataObj) {
		
		
		if(dataObj == null) {
			return null;
		}
		
		Calendar calendar = (Calendar) dataObj;
		
		return converter.getAsString(context, component, calendar.getTime());
	}

}
