package br.com.processador.impl;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.processador.dto.LinhaDTO;
import br.com.processador.entidades.Vendedor;
import emuns.TipoEnum;

@Component
public class ProcessadorVendedor implements ItemProcessor<LinhaDTO, Vendedor>{

	@Override
	public Vendedor process(LinhaDTO linha) throws Exception {
		if(linha.getId().contentEquals(TipoEnum.VENDEDOR.getCod())) {
			Vendedor vendedor = new Vendedor();
			
			vendedor.setId(linha.getId());
			vendedor.setCPF(linha.getP1());
			vendedor.setName(linha.getP2());
			vendedor.setSalary(new BigDecimal(linha.getP3()));
			
			return vendedor;
		}
		return null;
	}

}
