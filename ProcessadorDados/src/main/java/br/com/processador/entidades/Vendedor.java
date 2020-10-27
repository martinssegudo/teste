package br.com.processador.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



@Entity
@Table(name = "TB_VENDEDOR")
public class Vendedor implements Serializable{

	private static final long serialVersionUID = -6888172296492285096L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VENDEDOR")
	@GenericGenerator(
	        name = "SEQ_VENDEDOR",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SEQ_VENDEDOR"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@Column(name = "ID_VENDEDOR")
	private Long idBanco;
	
	@Column(name = "ID_ARQUIVO")
	private String id;
	
	@Column(name = "DS_NAME")
	private String name;
	
	@Column(name = "DS_CPF")
	private String CPF;
	
	@Digits(integer = 12, fraction = 2)
	@Column(name = "VL_SALARIO")	
	private BigDecimal salary;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
