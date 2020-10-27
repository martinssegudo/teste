package br.com.processador.impl;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.processador.dto.LinhaDTO;
import br.com.processador.entidades.Cliente;
import emuns.TipoEnum;

@Component
public class ProcessadorCliente implements ItemProcessor<LinhaDTO, Cliente>{

	@Override
	public Cliente process(LinhaDTO linha) throws Exception {
		if(linha.getId().equals(TipoEnum.CLIENTE.getCod())) {
			return montaCliente(linha);
		}
		return null;
	}

	private Cliente montaCliente(LinhaDTO linha) {
		Cliente cliente = new Cliente();
		
		cliente.setId(linha.getId());
		cliente.setCNPJ(linha.getP1());
		cliente.setName(linha.getP2());
		cliente.setBusArea(linha.getP3());
		
		return cliente;
	}
	 
	
}
