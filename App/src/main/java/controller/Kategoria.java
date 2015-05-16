package controller;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the kategoria database table.
 * 
 */
@Entity
@NamedQuery(name="Kategoria.findAll", query="SELECT k FROM Kategoria k")
public class Kategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String nazwa;

	//bi-directional many-to-one association to Lista
	@OneToMany(mappedBy="kategoria")
	private List<Lista> listas;

	public Kategoria() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public List<Lista> getListas() {
		return this.listas;
	}

	public void setListas(List<Lista> listas) {
		this.listas = listas;
	}

	public Lista addLista(Lista lista) {
		getListas().add(lista);
		lista.setKategoria(this);

		return lista;
	}

	public Lista removeLista(Lista lista) {
		getListas().remove(lista);
		lista.setKategoria(null);

		return lista;
	}

}