package br.com.processador.rservices;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.processador.repositorio.IVendaRepositorio;

@Transactional
@Service
public class VendaService {
	private IVendaRepositorio repositorio;
	
	@Autowired
	public VendaService(IVendaRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public Long getIdMaiorVenda() {
		Object[] retornoDaConsulta = (Object[]) repositorio.getVendaMaisCara();
		Object[] linha= null;
		if(retornoDaConsulta != null
				&& retornoDaConsulta.length > 0) {
			linha = (Object[]) retornoDaConsulta[0];
			
			return new Long((String) linha[0]);
		}
		return 0L;
	}
	
	public void revomeAll() {
		repositorio.deleteAll();
	}
}
