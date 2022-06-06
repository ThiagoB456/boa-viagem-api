
package br.com.etechoracio.boa_viagem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.entity.Viagem;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;
import br.com.etechoracio.boa_viagem.repository.ViagemRepository;

@Service
public class GastoService {
	@Autowired
	private GastoRepository repository;
	
	@Autowired
	private ViagemRepository viagemRepository;
	
	
	public List<Gasto> listarTodos(){
	return repository.findAll();
	}
	
	
	public Optional<Gasto> buscarPorID( long id) {
		return repository.findById(id); 
			
	}
		
	
	public boolean deletarID( long id) {
		boolean existe = repository.existsById(id);
		if(existe) {
			 repository.deleteById(id);		 
		} 
        	return existe;
	}
	
	
	public Gasto inserir( Gasto obj) {
		
		
		Optional<Viagem> viagem = viagemRepository.findById(obj.getViagem().getId());
		
		
		if(!viagem.isPresent()) {
			throw new RuntimeException("Viagem não encontrada");
		}
		if(viagem.get().getSaida() != null) {
			throw new RuntimeException("Viagem já foi fechada");
		}
		if(!viagem.get().getChegada().isAfter(obj.getData()))
		{
			throw new RuntimeException("Data do gasto for anterior à data de entrada da viagem");
	 			
		}
		return repository.save(obj); 
	}
	
	
	public  Optional<Gasto> atualizar( Long id, Gasto gasto) {
		
		boolean existe = repository.existsById(id);
		Optional<Viagem> viagem = viagemRepository.findById(gasto.getViagem().getId());
		
		
		if(!existe) {
			 return Optional.empty();
			 
		}
		if(!gasto.getViagem().getId().equals( viagem.get().getId())) {
			throw new RuntimeException("Viagem de atualização não for a mesma viagem da inserção");
		}
		
		if(!viagem.get().getChegada().isAfter(gasto.getData())) {
			throw new RuntimeException("Data do gasto for anterior à data de entrada da viagem");
		}
		return Optional.of(repository.save(gasto));
		
	}
	
}
