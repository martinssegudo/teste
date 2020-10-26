package br.com.processador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.processador.entidades.Item;

public interface IItemRepositorio extends JpaRepository<Item, Long>{

}
