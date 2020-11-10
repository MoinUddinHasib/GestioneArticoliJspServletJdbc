package it.gestionearticoli.model;

public class Categoria {
	
	private long id;
	private String descrizione;
	
	public Categoria(String descrizione) {
		this.descrizione = descrizione;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descrizione=" + descrizione + "]";
	}
	

}
