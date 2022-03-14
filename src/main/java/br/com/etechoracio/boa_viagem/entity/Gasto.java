package br.com.etechoracio.boa_viagem.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class Gasto { 
	 
    private Long id; 
 
    private String descricao; 
 
    private String local; 
 
    private String categoria; 
 
    private LocalDate gasto;         
    private Double valor; 
 
    private Viagem viagem; 
 
} 
 
