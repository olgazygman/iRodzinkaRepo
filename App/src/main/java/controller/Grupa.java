package controller;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the grupa database table.
 * 
 */
@Entity
@NamedQuery(name="Grupa.findAll", query="SELECT g FROM Grupa g")
public class Grupa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String nazwa;

	private Timestamp utworzona;

	private Timestamp zmodyfikowana;

	//bi-directional many-to-one association to Lista
	@OneToMany(mappedBy="grupa")
	private List<Lista> listas;

	//bi-directional many-to-one association to Uzytkownik
	@OneToMany(mappedBy="grupa")
	private List<Uzytkownik> uzytkowniks;

	public Grupa() {
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

	public Timestamp getUtworzona() {
		return this.utworzona;
	}

	public void setUtworzona(Timestamp utworzona) {
		this.utworzona = utworzona;
	}

	public Timestamp getZmodyfikowana() {
		return this.zmodyfikowana;
	}

	public void setZmodyfikowana(Timestamp zmodyfikowana) {
		this.zmodyfikowana = zmodyfikowana;
	}

	public List<Lista> getListas() {
		return this.listas;
	}

	public void setListas(List<Lista> listas) {
		this.listas = listas;
	}

	public Lista addLista(Lista lista) {
		getListas().add(lista);
		lista.setGrupa(this);

		return lista;
	}

	public Lista removeLista(Lista lista) {
		getListas().remove(lista);
		lista.setGrupa(null);

		return lista;
	}

	public List<Uzytkownik> getUzytkowniks() {
		return this.uzytkowniks;
	}

	public void setUzytkowniks(List<Uzytkownik> uzytkowniks) {
		this.uzytkowniks = uzytkowniks;
	}

	public Uzytkownik addUzytkownik(Uzytkownik uzytkownik) {
		getUzytkowniks().add(uzytkownik);
		uzytkownik.setGrupa(this);

		return uzytkownik;
	}

	public Uzytkownik removeUzytkownik(Uzytkownik uzytkownik) {
		getUzytkowniks().remove(uzytkownik);
		uzytkownik.setGrupa(null);

		return uzytkownik;
	}

}