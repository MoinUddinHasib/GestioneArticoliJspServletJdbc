package it.gestionearticoli.service.user;

import java.util.List;

import it.gestionearticoli.dao.user.UserDAO;
import it.gestionearticoli.model.User;

public interface UserService {

	// questo mi serve per injection
	public void setUserDao(UserDAO userDao);

	public List<User> listAll() throws Exception;

	public User findById(Long idInput) throws Exception;

	public int aggiorna(User input) throws Exception;

	public int inserisciNuovo(User input) throws Exception;

	public int rimuovi(User input) throws Exception;

	public List<User> findByExample(User input) throws Exception;
	

}
