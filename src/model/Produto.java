package model;

import java.sql.Timestamp;
import java.util.Observable;
import java.util.Observer;

import note.TitleNote;

public class Produto implements Observer {
	@TitleNote(name="title",  value = "Cód")
	private int idproduto;
	@TitleNote(name="title",  value = "Descrição")
	private String produto;
	@TitleNote(name="title",  value = "Código")
	private String codproduto;
	@TitleNote(name="title",  value = "")
	private int idunidade;
	@TitleNote(name="title",  value = "")
	private String unidade;
	@TitleNote(name="title",  value = "Sigla")
	private String sigla;
	@TitleNote(name="title",  value = "R$")
	private float preco;
	@TitleNote(name="title",  value = "Descição auxiliar")
	private String descricao;
	@TitleNote(name="title",  value = "Inserido por")
	private String uscadast;
	@TitleNote(name="title",  value = "Inserido em")
	private Timestamp dtcadast;
	@TitleNote(name="title",  value = "Alterador por")
	private String usmodifi;
	@TitleNote(name="title",  value = "Alterador em")
	private Timestamp dtmodifi;
	Observable pesquisa;
	
	public Produto() {

	}
	
	public Produto(Observable pesquisa) {
		super();
		this.pesquisa = pesquisa;		
		pesquisa.addObserver(this);
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

	public String getCodproduto() {
		return codproduto;
	}

	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
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

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		return "Produto [idproduto=" + idproduto + ", produto=" + produto + ", codproduto=" + codproduto
				+ ", idunidade=" + idunidade + ", unidade=" + unidade + ", sigla=" + sigla + ", preco=" + preco
				+ ", descricao=" + descricao + ", uscadast=" + uscadast + ", dtcadast=" + dtcadast + ", usmodifi="
				+ usmodifi + ", dtmodifi=" + dtmodifi + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codproduto == null) ? 0 : codproduto.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dtcadast == null) ? 0 : dtcadast.hashCode());
		result = prime * result + ((dtmodifi == null) ? 0 : dtmodifi.hashCode());
		result = prime * result + idproduto;
		result = prime * result + idunidade;
		result = prime * result + Float.floatToIntBits(preco);
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		Produto other = (Produto) obj;
		if (codproduto == null) {
			if (other.codproduto != null)
				return false;
		} else if (!codproduto.equals(other.codproduto))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
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
		if (idunidade != other.idunidade)
			return false;
		if (Float.floatToIntBits(preco) != Float.floatToIntBits(other.preco))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
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
		return true;
	}

	@Override
	public void update(Observable pesquisaSubject, Object arg) {
		if (pesquisaSubject instanceof Pesquisa) {
			Pesquisa pesquisa = (Pesquisa) pesquisaSubject;			
			System.out.println("idproduto: " + pesquisa.getRetorno());
		}		
	}
	
	
}
