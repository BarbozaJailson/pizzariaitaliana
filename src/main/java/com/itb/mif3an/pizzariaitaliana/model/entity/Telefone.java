package com.itb.mif3an.pizzariaitaliana.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Telefone")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "ddd", nullable = true)
    private String ddd;
	@Column(name = "numero", nullable = false, length = 12)
    private String numero;
	@Column(name = "tipo", nullable = true)
    private String tipo;
	@Column(name = "cod_status", nullable = false)
    private boolean codStatus;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = true)
	private Usuario usuario;

}
