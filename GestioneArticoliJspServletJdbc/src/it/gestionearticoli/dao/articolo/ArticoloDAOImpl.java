package it.gestionearticoli.dao.articolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

public class ArticoloDAOImpl extends AbstractMySQLDAO implements ArticoloDAO {

	@Override
	public List<Articolo> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from articolo");

			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				articoloTemp.setCategoria(MyServiceFactory.getCategoriaServiceInstance().findById(rs.getLong("categoria_fk")));
				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	@Override
	public List<Articolo> ArticoliFiltrati(Long categoria_fk) throws Exception {
		if (isNotActive()) {
			return null;
		}
		String query = "select * from articolo where categoria_fk = ?";
		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;

		try (PreparedStatement ps = connection.prepareStatement(query)){
			ps.setLong(1, categoria_fk);
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					articoloTemp = new Articolo();
					articoloTemp.setId(rs.getLong("ID"));
					articoloTemp.setCodice(rs.getString("CODICE"));
					articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
					articoloTemp.setPrezzo(rs.getInt("PREZZO"));
					articoloTemp.setCategoria(MyServiceFactory.getCategoriaServiceInstance().findById(categoria_fk));
					result.add(articoloTemp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Articolo get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		String query = "SELECT * FROM articolo WHERE id = ?";
        Articolo articoloResult = null;
        try (PreparedStatement ps = connection.prepareStatement(query)){
        	ps.setLong(1, id);
        	try(ResultSet resultSet = ps.executeQuery()){
        		if(resultSet.next()) {
        			articoloResult = new Articolo(resultSet.getString("codice"),
        					resultSet.getString("descrizione"),resultSet.getInt("prezzo"),
        					MyServiceFactory.getCategoriaServiceInstance().findById(resultSet.getLong("categoria_fk")));
        			articoloResult.setId(resultSet.getLong("id"));
        			}    		
        	}
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return articoloResult;
	}

	@Override
	public int update(Articolo input) throws Exception {
		if (isNotActive()) {
			return -1;
		}
		
		String query = "UPDATE articolo SET codice= ?, descrizione = ?, prezzo = ?, categoria_fk = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
        	ps.setString(1, input.getCodice());
        	ps.setString(2, input.getDescrizione());
        	ps.setInt(3, input.getPrezzo());
        	if(input.getCategoria() == null)
        		ps.setString(4,null);
        	else
        		ps.setLong(4, input.getCategoria().getId());
        	
        	ps.setLong(5, input.getId());
        	 return ps.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return -1;
	}

	@Override
	public int insert(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO articolo (codice, descrizione, prezzo, categoria_fk) VALUES (?, ?, ?, ?)")) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			if(input.getCategoria() == null)
        		ps.setString(4,null);
        	else
        		ps.setLong(4, input.getCategoria().getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "DELETE FROM articolo WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
        	ps.setLong(1, input.getId());
        	 return ps.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return -1;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<>();
		Articolo articoloTemp = null;
		String query = "select * from articolo where codice like ? and descrizione like ? and (prezzo = ? or ? is null) and (categoria_fk = ? or ? is null) ";
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, "%"+input.getCodice()+"%");
			ps.setString(2, "%"+input.getDescrizione()+"%");
			ps.setString(3, input.getPrezzo()==0?null:String.valueOf(input.getPrezzo()));
			ps.setString(4, input.getPrezzo()==0?null:String.valueOf(input.getPrezzo()));
			ps.setString(5, input.getCategoria()==null?null:String.valueOf(input.getCategoria().getId()));
			ps.setString(6, input.getCategoria()==null?null:String.valueOf(input.getCategoria().getId()));
			
			try(ResultSet resultSet = ps.executeQuery()){
				while (resultSet.next()) {
					articoloTemp = new Articolo();					
					articoloTemp.setId(resultSet.getLong("id"));
					articoloTemp.setCodice(resultSet.getString("CODICE"));
					articoloTemp.setDescrizione(resultSet.getString("DESCRIZIONE"));
					articoloTemp.setPrezzo(resultSet.getInt("PREZZO"));
					articoloTemp.setCategoria(MyServiceFactory.getCategoriaServiceInstance().findById(resultSet.getLong("categoria_fk")));
					result.add(articoloTemp);   		
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
