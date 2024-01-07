package com.otv.user.service;
import java.util.List;

import com.otv.model.mod.*;

public interface BalanceService {
	
	List<Balance> findAll();
	List<Balance> findByCriteria(String parameter);
	List<Total> findAllTotal();
	List<Total> findTotalByCriteria(String parameter);
	List<Total> findTotalByCriteriaLike(String parameter);
	void saveTotal(List<Total> listTotal);
	void saveSimulation1(List<Total> listTotal);
	void saveSimulation2(List<Total> listTotal);
	void saveSimulation3(List<Total> listTotal);
	List<Simulation1> findSimulation1();
	List<Simulation2> findSimulation2();
	List<Simulation3> findSimulation3();
	
}