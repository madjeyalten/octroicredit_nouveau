package com.otv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.otv.model.mod.Depensecourante;

@FacesConverter(forClass=Depensecourante.class, value="depenseConverter")
public class DepenseConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}




	}
