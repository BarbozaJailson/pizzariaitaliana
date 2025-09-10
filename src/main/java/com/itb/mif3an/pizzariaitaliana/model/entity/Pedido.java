package com.itb.mif3an.pizzariaitaliana.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ItemPedido> itemPedido;
}
