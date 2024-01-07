package com.otv.user.service;
import java.util.Date;
import java.util.List;


import com.otv.model.mod.*;

public interface DepenseService {
	
	List<Depensecourante> findAllDepenseCourante();	
	List<Depensecourante> findDepenseCouranteByCriteria(String parameter);
	List<Depenseexception> findAllDepenseException();	
	List<Depenseexception> findDepenseExceptionByCriteria(String parameter);
	void saveDepenseCourante(Depensecourante depensecourante);
	void saveDepenseException(Depenseexception depenseException);
	
	/**
	 * 
	 * Les revenus
	 */
	
	List<Revenucourant> findAllRevenucourant();	
	List<Revenucourant> findRevenucourantByCriteria(String parameter);
	List<Revenuexceptionnel> findAllRevenuexceptionnel();	
	List<Revenuexceptionnel> findRevenuexceptionnelByCriteria(String parameter);
	void saveRevenucourant(Revenucourant revenucourant);
	void saveRevenuexceptionnel(Revenuexceptionnel Revenuexceptionnel);
	
	/**
	 * 
	 * Revenu ou dépense
	 */
	
	List<Revenuordepense> findAllRevenuordepense();	
	List<Revenuordepense> findRevenuordepenseByCriteria(String parameter);	
	void saveDepenseOrRevenu(Revenuordepense revenuordepense);
	List<Revenuordepense> findRevenuordepenseByDate(Date inputDate);
	List<Revenuordepense> findRevenuByDate(Date inputDate);
	List<Revenuordepense> findDepenseDate(Date inputDate);
	
	List<Revenuordepense> findListRevReccurent();
	List<Revenuordepense> findDepRevReccurent();
	List<Revenuordepense> findRevorDepenseMoisSuivant(int currentValue);
	
	void deleteRevenuOrDepense(Revenuordepense revenuordepense);
	
	
	void saveUser(User user);
	boolean checkHabilitation(String login, String motDePasse);
	List<User> findUserByLogin(String login);
}