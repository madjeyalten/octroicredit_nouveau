package com.otv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.otv.model.mod.User;

@FacesConverter("exempleConverter")
public class ExempleConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		 User user = new User();
		 user.setNomAndPrenom(arg2);
	        return user;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		User user = (User) arg2;

        if(user == null) {
            return "";
        }

        return user.getNomAndPrenom();
	}

}
