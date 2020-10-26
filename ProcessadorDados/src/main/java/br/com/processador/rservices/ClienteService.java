package br.com.processador.rservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.processador.repositorio.IClienteRepositorio;

@Transactional
@Service
public class ClienteService {
	private IClienteRepositorio repositorio;
	
	@Autowired
	public ClienteService(IClienteRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public long contagemClientes() {
		return repositorio.count();
	}
	
	public void revomeAll() {
		repositorio.deleteAll();
	}
}
