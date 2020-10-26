package br.com.processador.rservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.processador.repositorio.IItemRepositorio;

@Transactional
@Service
public class ItemService {
	
	private IItemRepositorio repositorio;
	
	@Autowired
	public ItemService(IItemRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public void revomeAll() {
		repositorio.deleteAll();
	}
}
