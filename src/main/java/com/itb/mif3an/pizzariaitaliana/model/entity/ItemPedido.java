package com.itb.mif3an.pizzariaitaliana.model.entity;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itb.mif3an.pizzariaitaliana.util.BigDecimalDeserializer;

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
@Table(name = "ItemPedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "quantidade_item", nullable = true)
    private int quantidadeItem;
	@Column(name = "valor_unitario", nullable = false, columnDefinition = "DECIMAL(5,2)")
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private double valorUnitario;
	@Column(name = "cod_status", nullable = false)
    private boolean codStatus;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = true)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = true)
	private Pedido pedido;

}
