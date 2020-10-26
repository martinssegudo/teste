package br.com.processador.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import br.com.processador.entidades.Cliente;

public class CustomerItemSqlParameterSourceProvider implements ItemSqlParameterSourceProvider<Cliente>{

	@Override
    public SqlParameterSource createSqlParameterSource(Cliente cliente) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", cliente.getName());
        params.put("busArea", cliente.getBusArea());
        params.put("CNPJ", cliente.getCNPJ());
         
        return new MapSqlParameterSource(params);
    }
}
