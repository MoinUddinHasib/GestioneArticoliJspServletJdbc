package it.gestionearticoli.dao.articolo;

import java.util.List;
import it.gestionearticoli.dao.IBaseDAO;
import it.gestionearticoli.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo> {
	
	public List<Articolo> ArticoliFiltrati(Long categoria_fk) throws Exception;

}
