package br.com.processador.rservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.processador.repositorio.IVendaRepositorio;
import br.com.processador.repositorio.IVendedorRepositorio;

@Transactional
@Service
public class VendedorService {

	private IVendedorRepositorio repositorio;
	
	@Autowired
	public VendedorService(IVendedorRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public String getPiroVendedor() {
		return repositorio.getPiorVendedor();
	}
	
	public long contagemVendedores() {
		return repositorio.count();
	}
	
	public void revomeAll() {
		repositorio.deleteAll();
	}
}
