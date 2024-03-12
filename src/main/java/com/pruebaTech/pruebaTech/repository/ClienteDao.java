package com.pruebaTech.pruebaTech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pruebaTech.pruebaTech.model.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {

	@Query(value = "SELECT edad FROM dev_job.clientes", nativeQuery = true)
	int[] listaEdades();
}
