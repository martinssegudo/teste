package br.com.processador.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.processador.dto.LinhaDTO;
import br.com.processador.entidades.Item;
import emuns.TipoEnum;

@Component
public class ProcessadorItem implements ItemProcessor<LinhaDTO, Set<Item>>{

	@Override
	public Set<Item> process(LinhaDTO linha) throws Exception {
		if(linha.getId().contentEquals(TipoEnum.VENDA.getCod())) {
			return parserItem(linha.getP2(), linha.getP1());			
		}
		return null;
	}
		
	
	public Set<Item> parserItem(String itens, String idVenda) {
		Set<Item> itensConvertidos = new HashSet<Item>();
		
		itens = itens.replace("[","").replace("]", "");
		String [] itensVenda = itens.split(",");		
		for (String item : itensVenda) {
			Item itemMontado = montaItem(item,idVenda);
			if(!itensConvertidos.contains(itemMontado)) {
				itensConvertidos.add(itemMontado);
			}
			
		}
		return itensConvertidos;
	}
	
	
	private Item montaItem(String item, String idVenda) {
		String [] dadosItem = item.split("-");		
		Item itemMontado = new Item();
		
		itemMontado.setId(dadosItem[0]);
		itemMontado.setIdVenda(new Long(idVenda));
		itemMontado.setQtde(Long.parseLong(dadosItem[1]));
		itemMontado.setPreco(new BigDecimal(dadosItem[2]));
		
		return itemMontado;
	}

}
