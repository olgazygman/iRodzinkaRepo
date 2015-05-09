package controller;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lista database table.
 * 
 */
@Entity
@NamedQuery(name="Lista.findAll", query="SELECT l FROM Lista l")
public class Lista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private int ilosc;

	@Temporal(TemporalType.DATE)
	private Date kiedy;

	private String opis;

	private String stan;

	//bi-directional many-to-one association to Uzytkownik
	@ManyToOne
	private Uzytkownik uzytkownik;

	//bi-directional many-to-one association to Grupa
	@ManyToOne
	private Grupa grupa;

	//bi-directional many-to-one association to Kategoria
	@ManyToOne
	private Kategoria kategoria;

	public Lista() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIlosc() {
		return this.ilosc;
	}

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	public Date getKiedy() {
		return this.kiedy;
	}

	public void setKiedy(Date kiedy) {
		this.kiedy = kiedy;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getStan() {
		return this.stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public Uzytkownik getUzytkownik() {
		return this.uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public Grupa getGrupa() {
		return this.grupa;
	}

	public void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}

	public Kategoria getKategoria() {
		return this.kategoria;
	}

	public void setKategoria(Kategoria kategoria) {
		this.kategoria = kategoria;
	}

}