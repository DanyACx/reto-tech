package com.pruebaTech.pruebaTech.service.serviceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebaTech.pruebaTech.model.Cliente;
import com.pruebaTech.pruebaTech.repository.ClienteDao;
import com.pruebaTech.pruebaTech.service.ICliente;

@Service
public class ClienteImpl implements ICliente{

	@Autowired
	private ClienteDao clienteDao;
	
	@Transactional(readOnly = true) // solo para consulta
	public List<Cliente> listarClientes(){
		
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Transactional
	public Cliente guardarCliente(Cliente cliente) {
		clienteDao.save(cliente);
		return cliente;
	}
	
	public ResponseEntity<?> kpiClientes() {
		Double promedio, sumaCuadradosDiferencias, desviacionEstandar;
		
		int[] listaEdades = clienteDao.listaEdades();
		promedio = Arrays.stream(listaEdades).average().orElse(Double.NaN);
		
		
		sumaCuadradosDiferencias = Arrays.stream(listaEdades)
                .mapToDouble(n -> Math.pow(n - promedio, 2))
                .sum();
		
		desviacionEstandar = Math.sqrt(sumaCuadradosDiferencias / listaEdades.length);
		
		Map<String, Double> response = new HashMap<>();
		response.put("Promedio", promedio);
		response.put("Desviación", desviacionEstandar);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
}
