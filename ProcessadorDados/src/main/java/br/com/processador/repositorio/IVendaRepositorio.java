package br.com.processador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.processador.entidades.Venda;

public interface IVendaRepositorio extends JpaRepository<Venda, Long> {

	@Query(value = "select ID_VENDA_ARQUIVO , SUM((VL_PRECO *VL_QTDE)) valor " + 
			"from TB_VENDA v, TB_ITEM i  " + 
			"where i.ID_VENDA = v.ID_VENDA_ARQUIVO " + 
			"GROUP BY ID_VENDA_ARQUIVO  " + 
			"order by valor desc " + 
			"limit 1",
			nativeQuery = true)
	public Object[] getVendaMaisCara();
}
