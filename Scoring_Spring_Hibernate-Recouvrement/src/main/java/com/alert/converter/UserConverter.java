package com.alert.converter;

import java.util.List;
import java.util.StringTokenizer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otv.model.mod.User;



@FacesConverter("userConverter")
public class UserConverter implements Converter {

	 @Override
	    public Object getAsObject(FacesContext arg0, UIComponent component, String stringvalue) {
		 User user = new User();
		 user.setNomAndPrenom(stringvalue);
	        return user;
	    }

	    @Override
	    public String getAsString(FacesContext arg0, UIComponent component, Object objectvalue) {
	    	User user = (User) objectvalue;

	        if(user == null) {
	            return "";
	        }

	        return user.getNomAndPrenom();
	    }

	    
	}
