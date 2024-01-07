package com.otv.user.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otv.model.mod.Client;
import com.otv.model.mod.Seuilscore;

@Service("seuilScoreService")
@Transactional
public class SeuilScoreServiceImpl implements SeuilScoreService {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void saveSeuilScore(Seuilscore seuilscore) {
		sessionFactory.getCurrentSession().saveOrUpdate(seuilscore);	
		
	}

	public List<Seuilscore> findSeuilscore(Seuilscore Seuilscore) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Seuilscore.class);
		return crit.list();
	}

}
