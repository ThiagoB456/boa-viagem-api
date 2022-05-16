package br.com.etechoracio.boa_viagem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.service.GastoService;

@RestController
@RequestMapping("/gastos")
public class GastoController {

	@Autowired
	private GastoService service;
	
	@GetMapping
	public List<Gasto> listarTodos(){
	return service.listarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Gasto> buscarPorID(@PathVariable long id) {
		Optional<Gasto> existe = service.buscarPorID(id); 
		
		if(existe.isPresent()) {
			return ResponseEntity.ok(existe.get());
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Gasto> deletarID(@PathVariable long id) {
		boolean existe = service.deletarID(id);
		if(existe) {
			 return ResponseEntity.ok().build();
		} 
            return ResponseEntity.notFound().build();		
		
	}
	
	@PostMapping
 	public ResponseEntity<Gasto> inserir(@RequestBody Gasto obj) {
 		service.inserir(obj);
 		return ResponseEntity.ok(obj);
 	
 		
 	}
	@PutMapping("/{id}")
	public ResponseEntity<Gasto> atualizar(@PathVariable Long id,@RequestBody Gasto gasto) {
		Optional<Gasto> existe = service.atualizar(id, gasto);
		
		if(!existe.isPresent()) {
			 return ResponseEntity.notFound().build();
			 
		}
		
		return ResponseEntity.ok(gasto);
		
	}
	
	}
