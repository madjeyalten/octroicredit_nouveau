package com.otv.converter;

import java.util.List;
import java.util.StringTokenizer;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otv.model.mod.Depensecourante;
import com.otv.model.mod.User;
import com.otv.user.service.ScoringService;
@SessionScoped
//@FacesConverter(forClass=UserConverter.class, value="userConverter")
@FacesConverter("userConverter")
public class UserConverter implements Converter {
	
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
