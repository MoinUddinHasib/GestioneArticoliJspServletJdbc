package it.gestionearticoli.dao.user;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.User;

public class UserDAOImpl extends AbstractMySQLDAO implements UserDAO {

	@Override
	public User get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		String query = "SELECT * FROM user WHERE id_user = ?";
		User us = null;
        try (PreparedStatement ps = connection.prepareStatement(query)){
        	ps.setLong(1, id);
        	try(ResultSet resultSet = ps.executeQuery()){
        		if(resultSet.next()) {
        			us = new User(id, resultSet.getString("nome"), resultSet.getString("cognome"),
        					resultSet.getString("codice_fiscale"), resultSet.getString("password"), resultSet.getString("ruolo"));
        			}    		
        	}
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return us;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

	@Override
	public List<User> list() throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserDAOImpl");
		return null;
	}

	@Override
	public int update(User input) throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserDAOImpl");
		return 0;
	}

	@Override
	public int insert(User input) throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserDAOImpl");
		return 0;
	}

	@Override
	public int delete(User input) throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserDAOImpl");
		return 0;
	}

	@Override
	public List<User> findByExample(User input) throws Exception {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(f,"UserDAOImpl");
		return null;
	}


}
