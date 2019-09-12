package model;

import java.sql.Timestamp;

import note.TitleNote;

public class ServicoProduto {
	@TitleNote(name="title",  value = "Seq")
	private int idservicoproduto;
	@TitleNote(name="title",  value = "OS")
	private int idservico;
	@TitleNote(name="title",  value = "Cód")
	private int idproduto;	
	@TitleNote(name="title",  value = "Descrição")
	private String produto;
	@TitleNote(name="title",  value = "")
	private int idunidade;
	@TitleNote(name="title",  value = "")
	private String unidade;
	@TitleNote(name="title",  value = "Sigla")
	private String sigla;
	@TitleNote(name="title",  value = "Qtd")	
	private double qtd;
	@TitleNote(name="title",  value = "Preço")
	private double preco;
	@TitleNote(name="title",  value = "Valor")
	private float valor;	
	@TitleNote(name="title",  value = "Inserido por")
	private String uscadast;
	@TitleNote(name="title",  value = "Inserido em")
	private Timestamp dtcadast;
	@TitleNote(name="title",  value = "Alterador por")
	private String usmodifi;
	@TitleNote(name="title",  value = "Alterador em")
	private Timestamp dtmodifi;

	public ServicoProduto() {
		
	}

	public int getIdservicoproduto() {
		return idservicoproduto;
	}

	public void setIdservicoproduto(int idservicoproduto) {
		this.idservicoproduto = idservicoproduto;
	}

	public int getIdservico() {
		return idservico;
	}

	public void setIdservico(int idservico) {
		this.idservico = idservico;
	}

	public int getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getIdunidade() {
		return idunidade;
	}

	public void setIdunidade(int idunidade) {
		this.idunidade = idunidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		this.qtd = qtd;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
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
		return "ServicoProduto [idservicoproduto=" + idservicoproduto + ", idservico=" + idservico + ", idproduto="
				+ idproduto + ", produto=" + produto + ", idunidade=" + idunidade + ", unidade=" + unidade + ", sigla="
				+ sigla + ", qtd=" + qtd + ", preco=" + preco + ", valor=" + valor + ", uscadast=" + uscadast
				+ ", dtcadast=" + dtcadast + ", usmodifi=" + usmodifi + ", dtmodifi=" + dtmodifi + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtcadast == null) ? 0 : dtcadast.hashCode());
		result = prime * result + ((dtmodifi == null) ? 0 : dtmodifi.hashCode());
		result = prime * result + idproduto;
		result = prime * result + idservico;
		result = prime * result + idservicoproduto;
		result = prime * result + idunidade;
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		temp = Double.doubleToLongBits(qtd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		result = prime * result + ((uscadast == null) ? 0 : uscadast.hashCode());
		result = prime * result + ((usmodifi == null) ? 0 : usmodifi.hashCode());
		result = prime * result + Float.floatToIntBits(valor);
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
		ServicoProduto other = (ServicoProduto) obj;
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
		if (idproduto != other.idproduto)
			return false;
		if (idservico != other.idservico)
			return false;
		if (idservicoproduto != other.idservicoproduto)
			return false;
		if (idunidade != other.idunidade)
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (Double.doubleToLongBits(qtd) != Double.doubleToLongBits(other.qtd))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
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
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}

}
