package br.com.processador.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.processador.dto.LinhaDTO;
import br.com.processador.entidades.Item;
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
		
	
	public Set<Item> parserItem(String itens) {
		Set<Item> itensConvertidos = new HashSet<Item>();
		
		itens.replace("[","").replace("]", "");
		String [] itensVenda = itens.split(",");		
		for (String item : itensVenda) {
			itensConvertidos.add(montaItem(item));
		}
		return itensConvertidos;
	}
	
	
	private Item montaItem(String item) {
		String [] dadosItem = item.split("-");		
		Item itemMontado = new Item();
		
		itemMontado.setId(dadosItem[0]);
		itemMontado.setQtde(Long.parseLong(dadosItem[1]));
		itemMontado.setPreco(new BigDecimal(dadosItem[2]));
		
		return itemMontado;
	}
}
