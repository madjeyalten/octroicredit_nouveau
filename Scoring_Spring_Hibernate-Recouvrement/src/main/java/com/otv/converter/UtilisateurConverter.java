package com.otv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import com.otv.model.mod.User;
import com.otv.user.service.ScoringService;

@FacesConverter(forClass=UtilisateurConverter.class, value="utilisateurConverter")
public class UtilisateurConverter implements Converter {

	@Autowired
	private transient ScoringService scoringService;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String stringvalue) {
		 User user = new User();
		 user.setNomAndPrenom(stringvalue);
	        return user;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object objectvalue) {
   	User user = (User) objectvalue;

       if(user == null) {
           return "";
       }

       return user.getNomAndPrenom();
	}

	public ScoringService getScoringService() {
		return scoringService;
	}

	public void setScoringService(ScoringService scoringService) {
		this.scoringService = scoringService;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	    
	}
