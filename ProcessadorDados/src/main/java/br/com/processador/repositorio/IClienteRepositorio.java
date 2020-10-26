package br.com.processador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.processador.entidades.Cliente;

public interface IClienteRepositorio extends JpaRepository<Cliente, Long>{	
}
