package it.gestionearticoli.service.user;

import java.sql.Connection;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.user.UserDAO;
import it.gestionearticoli.model.User;

public class UserServiceImpl implements UserService {

	private UserDAO userDao;
	
	@Override
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findById(Long idInput) throws Exception {
		User result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			userDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = userDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<User> listAll() throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserServiceImpl");
		return null;
	}

	@Override
	public int aggiorna(User input) throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserServiceImpl");
		return 0;
	}

	@Override
	public int inserisciNuovo(User input) throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserServiceImpl");
		return 0;
	}

	@Override
	public int rimuovi(User input) throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserServiceImpl");
		return 0;
	}

	@Override
	public List<User> findByExample(User input) throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserServiceImpl");
		return null;
	}

	
}
