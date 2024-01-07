package com.otv.user.service;
import java.util.Date;
import java.util.List;


import com.otv.model.mod.*;

public interface ScoringService {
	
	List<Scoring> findScoringByCriteria(String parameter);
	List<Client> findClientByCriteria(String nom,Integer identifiant);
	void saveScoring(Scoring scoring);
	void saveScoringParametrage(Scoreparametrage Scoreparametrage);
	List<Scoreparametrage> findsaveScoringParametrage();
	void saveClient(Client client);
	void saveUser(User user);
	boolean checkHabilitation(String login, String motDePasse);
	List<User> findUserByLogin(String login);
	List<User> findUserByName(String nom);
	List<User> findAllUser();
	void saveCredit(Credit credit);
	List<Credit> findCreditByStatus(String status);
	List<Credit> findCreditByEnvoyerVers(String status);
	List<Historique> findHistoriqueById(String identifier);
	void saveHistorique(Historique historique);
	void saveSeuilScore(Seuilscore seuilscore);
	List<Seuilscore> findSeuilscore(Seuilscore Seuilscore);
}