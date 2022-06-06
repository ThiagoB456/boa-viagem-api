package br.com.etechoracio.boa_viagem.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.etechoracio.boa_viagem.enums.TipoViagemEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TBL_VIAGEM")
public class Viagem {
	
		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 @Column(name="ID_VIAGEM")
	    private Long id; 
	 
		 @Length(max=30)
		 @NotNull
		 @Column(name="TX_DESTINO")
	    private String destino; 
	 
		 @Length(max=8)
		 @NotBlank(message="Campo local é obrigatório")
		 @Enumerated(EnumType.STRING)
		 @Column(name="TP_VIAGEM")
	    private TipoViagemEnum tipo; 
	 
		 @FutureOrPresent
		 @NotNull
		 @Column(name="DT_CHEGADA")
	    private LocalDate chegada; 
	 
		 @FutureOrPresent
		 @Column(name="DT_SAIDA")
	    private LocalDate saida; 
	 
		 @DecimalMin(value="0.01")
		 @NotNull
		 @Column(name="VLR_ORCAMENTO")
	    private Double orcamento; 
	 
		 @Min(value = 1)
		 @Column(name="QTD_PESSOAS")
	    private Integer pessoas; 
	 	
}