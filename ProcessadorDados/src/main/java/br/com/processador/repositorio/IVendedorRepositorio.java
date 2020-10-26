package br.com.processador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.processador.entidades.Vendedor;

public interface IVendedorRepositorio extends JpaRepository<Vendedor, Long> {

	@Query(value = "select t.ds_vendedor " + 
			"from TB_VENDEDOR vo " + 
			"    JOIN (  " + 
			"select DS_VENDEDOR, SUM((VL_PRECO *VL_QTDE)) valor " + 
			"from TB_VENDA v, TB_ITEM i  " + 
			"where i.ID_VENDA = v.ID_VENDA_ARQUIVO " + 
			"GROUP BY DS_VENDEDOR " + 
			"limit 1) T on t.DS_VENDEDOR = vo.DS_NAME  " + 
			"order by t.valor " + 
			"limit 1", 
		   nativeQuery = true)
	public String getPiorVendedor();
}
