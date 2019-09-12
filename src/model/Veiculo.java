package model;

import java.sql.Timestamp;
import java.util.Observable;
import java.util.Observer;

import note.TitleNote;

public class Veiculo implements Observer {
	@TitleNote(name="title",  value = "Cód")
	private int idveiculo;
	@TitleNote(name="title",  value = "Placa")
	private String placa;
	@TitleNote(name="title",  value = "Marca")
	private String marca;
	@TitleNote(name="title",  value = "Modelo")
	private String modelo;
	@TitleNote(name="title",  value = "Inserido por")
	private String uscadast;
	@TitleNote(name="title",  value = "Inserido em")
	private Timestamp dtcadast;
	@TitleNote(name="title",  value = "Alterado por")
	private String usmodifi;
	@TitleNote(name="title",  value = "Alterado em")
	private Timestamp dtmodifi;
	
	Observable pesquisa;
	
	public Veiculo() {
		
	}

	public Veiculo(Observable pesquisa) {
		super();
		this.pesquisa = pesquisa;		
		pesquisa.addObserver(this);
	}

	public int getIdveiculo() {
		return idveiculo;
	}

	public void setIdveiculo(int idveiculo) {
		this.idveiculo = idveiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getUscadast() {
		return uscadast;
	}

	public void setUscadast(String uscadast) {
		this.uscadast = uscadast;
	}

	public Timestamp getDtcadast() {
		return dtcadast;
	}

	public void setDtcadast(Timestamp dtcadast) {
		this.dtcadast = dtcadast;
	}

	public String getUsmodifi() {
		return usmodifi;
	}

	public void setUsmodifi(String usmodifi) {
		this.usmodifi = usmodifi;
	}

	public Timestamp getDtmodifi() {
		return dtmodifi;
	}

	public void setDtmodifi(Timestamp dtmodifi) {
		this.dtmodifi = dtmodifi;
	}
		
	public Observable getPesquisa() {
		return pesquisa;
	}

	@Override
	public String toString() {
		return "Veiculo [idveiculo=" + idveiculo + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo
				+ ", uscadast=" + uscadast + ", dtcadast=" + dtcadast + ", usmodifi=" + usmodifi + ", dtmodifi="
				+ dtmodifi + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtcadast == null) ? 0 : dtcadast.hashCode());
		result = prime * result + ((dtmodifi == null) ? 0 : dtmodifi.hashCode());
		result = prime * result + idveiculo;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((uscadast == null) ? 0 : uscadast.hashCode());
		result = prime * result + ((usmodifi == null) ? 0 : usmodifi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (dtcadast == null) {
			if (other.dtcadast != null)
				return false;
		} else if (!dtcadast.equals(other.dtcadast))
			return false;
		if (dtmodifi == null) {
			if (other.dtmodifi != null)
				return false;
		} else if (!dtmodifi.equals(other.dtmodifi))
			return false;
		if (idveiculo != other.idveiculo)
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (uscadast == null) {
			if (other.uscadast != null)
				return false;
		} else if (!uscadast.equals(other.uscadast))
			return false;
		if (usmodifi == null) {
			if (other.usmodifi != null)
				return false;
		} else if (!usmodifi.equals(other.usmodifi))
			return false;
		return true;
	}

	@Override
	public void update(Observable pesquisaSubject, Object arg) {		
		if (pesquisaSubject instanceof Pesquisa) {
			Pesquisa pesquisa = (Pesquisa) pesquisaSubject;			
			System.out.println("idveiculo: " + pesquisa.getRetorno());
		}
	}	

}
