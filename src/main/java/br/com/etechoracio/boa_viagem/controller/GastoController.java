package br.com.etechoracio.boa_viagem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;

@RestController
@RequestMapping("/gastos")
public class GastoController {

	@Autowired
	private GastoRepository repository;
	
	@GetMapping
	public List<Gasto> listarTodos(){
	return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Gasto buscarPorID(@PathVariable long id) {
		return repository.findById(id).orElse(null); 
	}
	
	@DeleteMapping("/{id}")
	public void deletarID(@PathVariable long id) {
		 repository.deleteById(id);
	}
	}
