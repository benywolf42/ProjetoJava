package com.biblioteca.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converteData")
public class ConverteData implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String param) {
		if(param!=null){
			Calendar data = null;
			Date dataDate;
			try {
				dataDate = new SimpleDateFormat("dd/MM/yyyy").parse(param);
				data = Calendar.getInstance();
				data.setTime(dataDate);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
						
			return data;
		}
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object data) {
		String str=null;
		Calendar calendar = (Calendar)data;
		if(data!=null){
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			str = fmt.format(calendar.getTime());
			return str;
		}
		return null;
	}

}
