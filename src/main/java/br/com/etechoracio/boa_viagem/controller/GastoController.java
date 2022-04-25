package br.com.etechoracio.boa_viagem.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Gasto> buscarPorID(@PathVariable long id) {
		Optional<Gasto> existe = repository.findById(id); 
		
		if(existe.isPresent()) {
			return ResponseEntity.ok(existe.get());
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarID(@PathVariable long id) {
		boolean existe = repository.existsById(id);
		if(existe) {
			 repository.deleteById(id);
			 return ResponseEntity.ok().build();
		} 
            return ResponseEntity.notFound().build();		
		
	}
	
	@PostMapping
 	public ResponseEntity<Gasto> inserir(@RequestBody Gasto obj) {
 		repository.save(obj);
 		return ResponseEntity.ok(obj);
 	
 		
 	}
	}
