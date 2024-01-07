package com.otv.user.service;
import java.util.Date;
import java.util.List;


import com.otv.model.mod.*;

public interface SeuilScoreService {

	
	void saveSeuilScore(Seuilscore seuilscore);
	
	List<Seuilscore> findSeuilscore(Seuilscore Seuilscore);
}