package br.com.processador.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "TB_ITEM")
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6140623105329303272L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM")
	@GenericGenerator(
	        name = "SEQ_ITEM",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SEQ_ITEM"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@Column(name = "ID_ITEM")
	private Long idBanco;
	
	@Column(name = "ID_VENDA")
	private Long idVenda;
	
	@Column(name = "NM_ITEM")
	private String id;
	
	@Column(name = "VL_QTDE")
	private Long qtde;
	
	@Digits(integer = 12, fraction = 2)
	@Column(name = "VL_PRECO")
	private BigDecimal preco;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getQtde() {
		return qtde;
	}

	public void setQtde(Long qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idVenda == null) {
			if (other.idVenda != null)
				return false;
		} else if (!idVenda.equals(other.idVenda))
			return false;
		return true;
	}
	
	

}
