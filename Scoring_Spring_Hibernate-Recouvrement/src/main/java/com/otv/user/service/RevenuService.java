package com.otv.user.service;

import java.util.List;

import com.otv.model.mod.Depensecourante;
import com.otv.model.mod.Depenseexception;
import com.otv.model.mod.Revenucourant;
import com.otv.model.mod.Revenuexceptionnel;

public interface RevenuService {
	List<Revenucourant> findAllRevenucourant();	
	List<Revenucourant> findRevenucourantByCriteria(String parameter);
	List<Revenuexceptionnel> findAllRevenuexceptionnel();	
	List<Revenuexceptionnel> findRevenuexceptionnelByCriteria(String parameter);
	void saveRevenucourant(Revenucourant revenucourant);
	void saveRevenuexceptionnel(Revenuexceptionnel revenuexceptionnel);
}
