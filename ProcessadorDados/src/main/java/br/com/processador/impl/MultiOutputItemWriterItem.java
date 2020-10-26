package br.com.processador.impl;

import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.processador.entidades.Item;

public class MultiOutputItemWriterItem implements ItemWriter<Set<Item>>{
	
	private JdbcTemplate jdbcTemplate;

    public MultiOutputItemWriterItem(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }	
		
	
	@Override
	public void write(List<? extends Set<Item>> items) throws Exception {
		items.forEach(
				lista -> {
					lista.forEach(
							item -> {
								StringBuilder sb = new StringBuilder();    	
						    	sb.append("INSERT INTO TB_ITEM ");
						    	sb.append(" (ID_ITEM, NM_ITEM, VL_QTDE, VL_PRECO, ID_VENDA ) ");
						    	sb.append(" VALUES ");
						    	sb.append("( ");
						    	sb.append(" NEXT VALUE FOR SEQ_ITEM, ");
						    	sb.append(item.getId()+","); 
						    	sb.append(item.getQtde()+","); 
						    	sb.append(item.getPreco()+","); 
						    	sb.append(item.getIdVenda()+")");
						    	
						    	jdbcTemplate.batchUpdate(sb.toString());
							}
					);					
				}
		);
	}

}
