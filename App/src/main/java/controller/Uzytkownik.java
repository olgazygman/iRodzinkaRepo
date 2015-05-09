package controller;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uzytkownik database table.
 * 
 */
@Entity
@NamedQuery(name="Uzytkownik.findAll", query="SELECT u FROM Uzytkownik u")
public class Uzytkownik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String imie;

	//bi-directional many-to-one association to Lista
	@OneToMany(mappedBy="uzytkownik")
	private List<Lista> listas;

	//bi-directional many-to-one association to Grupa
	@ManyToOne
	private Grupa grupa;

	public Uzytkownik() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public List<Lista> getListas() {
		return this.listas;
	}

	public void setListas(List<Lista> listas) {
		this.listas = listas;
	}

	public Lista addLista(Lista lista) {
		getListas().add(lista);
		lista.setUzytkownik(this);

		return lista;
	}

	public Lista removeLista(Lista lista) {
		getListas().remove(lista);
		lista.setUzytkownik(null);

		return lista;
	}

	public Grupa getGrupa() {
		return this.grupa;
	}

	public void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}

}