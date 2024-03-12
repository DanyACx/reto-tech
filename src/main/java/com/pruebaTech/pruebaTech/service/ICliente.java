package com.pruebaTech.pruebaTech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pruebaTech.pruebaTech.model.Cliente;

public interface ICliente {

	Cliente guardarCliente(Cliente cliente);
	
	List<Cliente> listarClientes();
	
	ResponseEntity<?> kpiClientes();
	
}
