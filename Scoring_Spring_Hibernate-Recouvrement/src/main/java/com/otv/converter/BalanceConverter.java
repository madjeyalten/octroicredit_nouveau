package com.otv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.otv.model.mod.Balance;
import com.otv.model.mod.Total;

@FacesConverter(forClass=Total.class, value="balanceConverter")
public class BalanceConverter implements Converter {

	 @Override
	    public Object getAsObject(FacesContext arg0, UIComponent component, String stringvalue) {
	        Total total = new Total();
	        return total;
	    }

	    @Override
	    public String getAsString(FacesContext arg0, UIComponent component, Object objectvalue) {
	    	Total total = (Total) objectvalue;

	        if(total == null) {
	            return "";
	        }

	        return total.getId().getCompte();
	    }
	}
