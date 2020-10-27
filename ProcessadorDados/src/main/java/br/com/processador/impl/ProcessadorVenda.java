package br.com.processador.impl;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.processador.dto.LinhaDTO;
import br.com.processador.entidades.Venda;
import emuns.TipoEnum;

@Component
public class ProcessadorVenda implements ItemProcessor<LinhaDTO, Venda>{

	@Override
	public Venda process(LinhaDTO linha) throws Exception {
		if(linha.getId().contentEquals(TipoEnum.VENDA.getCod())) {
			Venda venda = new Venda();
			
			venda.setId(linha.getId());
			venda.setIdVenda(linha.getP1());			
			venda.setVendedor(linha.getP3());
			
			return venda;
		}
		return null;
	}
}
