package model;

import java.sql.Timestamp;

import note.IndexNote;
import note.TitleNote;

public class Etapa {
	@TitleNote(name="title",  value = "Cód")
	@IndexNote(name="idetapa",  index = 0)
	private int idetapa;
	@TitleNote(name="title",  value = "etapa")
	@IndexNote(name="etapa",  index = 1)
	private String etapa; 
	@TitleNote(name="title",  value = "Inserido por")
	@IndexNote(name="uscadast",  index = 2)
	private String uscadast;	
	@TitleNote(name="title",  value = "Inserido em")
	@IndexNote(name="dtcadast",  index = 3)
	private Timestamp dtcadast;	
	@TitleNote(name="title",  value = "Alterador por")
	@IndexNote(name="usmodifi",  index = 4)
	private String usmodifi;	
	@TitleNote(name="title",  value = "Alterador em")
	@IndexNote(name="dtmodifi",  index = 5)
	private Timestamp dtmodifi;
	
	public Etapa() {
		
	}

	public int getIdetapa() {
		return idetapa;
	}

	public void setIdetapa(int idetapa) {
		this.idetapa = idetapa;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
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

	@Override
	public String toString() {
		return "Etapa [idetapa=" + idetapa + ", etapa=" + etapa + ", uscadast=" + uscadast + ", dtcadast=" + dtcadast
				+ ", usmodifi=" + usmodifi + ", dtmodifi=" + dtmodifi + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtcadast == null) ? 0 : dtcadast.hashCode());
		result = prime * result + ((dtmodifi == null) ? 0 : dtmodifi.hashCode());
		result = prime * result + ((etapa == null) ? 0 : etapa.hashCode());
		result = prime * result + idetapa;
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
		Etapa other = (Etapa) obj;
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
		if (etapa == null) {
			if (other.etapa != null)
				return false;
		} else if (!etapa.equals(other.etapa))
			return false;
		if (idetapa != other.idetapa)
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
	

}
