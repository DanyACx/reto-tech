package com.pruebaTech.pruebaTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaTech.pruebaTech.model.Cliente;
import com.pruebaTech.pruebaTech.service.ICliente;


@RestController
@RequestMapping("/api/v1")
public class ClienteControlador {

	private final ICliente clienteServicio;
	
	@Autowired
	public ClienteControlador(ICliente clienteServicio) {
		this.clienteServicio = clienteServicio; // una vez que se asigna un valor a ese campo, no puede ser modificado
		// estás asegurando que su valor no cambiará después de la inicialización
	}
	
	@GetMapping("/listclientes")
	public List<Cliente> listarClientes(){
		return clienteServicio.listarClientes();
	}
	
	@PostMapping("/creacliente")
	public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
		
		try {
			Cliente clienteGuardado = clienteServicio.guardarCliente(cliente);
			return new ResponseEntity<>(clienteGuardado, HttpStatus.CREATED);
		}
		catch(Exception e){
			// Maneja la excepción y responde con un código específico
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kpideclientes")
	public ResponseEntity<?> kpiClientes() {
		
		try {
			return clienteServicio.kpiClientes();
		}
		catch(Exception e){
			// Maneja la excepción y responde con un código específico
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
