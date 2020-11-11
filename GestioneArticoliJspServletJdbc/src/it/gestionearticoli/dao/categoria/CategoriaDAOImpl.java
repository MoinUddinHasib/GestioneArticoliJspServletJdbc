package it.gestionearticoli.dao.categoria;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaDAOImpl extends AbstractMySQLDAO implements CategoriaDAO {

	@Override
	public List<Categoria> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from categoria");

			while (rs.next()) {
				categoriaTemp = new Categoria(rs.getString("descrizione"));
				categoriaTemp.setId(rs.getLong("id_categoria"));
				result.add(categoriaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Categoria get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		String query = "SELECT * FROM categoria WHERE id_categoria = ?";
        Categoria categoriaResult = null;
        try (PreparedStatement ps = connection.prepareStatement(query)){
        	ps.setLong(1, id);
        	try(ResultSet resultSet = ps.executeQuery()){
        		if(resultSet.next()) {
        			categoriaResult = new Categoria(resultSet.getString("descrizione"));
        			categoriaResult.setId(resultSet.getLong("id_categoria"));
        			}    		
        	}
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return categoriaResult;
	}

	@Override
	public int update(Categoria input) throws Exception {
		if (isNotActive()) {
			return -1;
		}
		
		String query = "UPDATE categoria SET descrizione = ? WHERE id_categoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
        	ps.setString(1, input.getDescrizione());
        	ps.setLong(2, input.getId());
        	 return ps.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return -1;
	}

	@Override
	public int insert(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO categoria (descrizione) VALUES (?)")) {
			ps.setString(1, input.getDescrizione());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "DELETE FROM categoria WHERE id_categoria = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
        	ps.setLong(1, input.getId());
        	 return ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return -1;
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;
		String query = "select * from categoria where descrizione like ?";
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, "%"+input.getDescrizione()+"%");
			
			try(ResultSet resultSet = ps.executeQuery()){
				while (resultSet.next()) {
					categoriaTemp = new Categoria(resultSet.getString("descrizione"));
					categoriaTemp.setId(resultSet.getLong("id_categoria"));
					result.add(categoriaTemp);   		
        	}
		}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	
}
