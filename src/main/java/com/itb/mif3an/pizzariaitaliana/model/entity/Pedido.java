package com.itb.mif3an.pizzariaitaliana.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "data_hora_pedido", nullable = false)
    private LocalDateTime dataHoraPedido;
	@Column(name = "data_hora_entrada", nullable = true)
    private LocalDateTime dataHoraEntrega;
	@Column(name = "status", nullable = true, length = 20)
    private String status;
	@Column(name = "valor_pedido", nullable = true, columnDefinition = "DECIMAL(5,2)")
    private BigDecimal valorPedido;
	@Column(name = "cod_status", nullable = true)
    private boolean codStatus;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ItemPedido> itemPedido;
}
