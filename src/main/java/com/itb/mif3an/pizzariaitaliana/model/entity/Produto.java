package com.itb.mif3an.pizzariaitaliana.model.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itb.mif3an.pizzariaitaliana.util.BigDecimalDeserializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false, length = 45)
    private String nome;
	@Column(nullable = true, length = 255)
    private String descricao;
	@Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
	@JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal valorVenda;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal valorCompra;
    @Column(nullable = true, length = 20)
    private String tipo;
    @JsonIgnore
    private int quantidadeEstoque;
    @Column(nullable = false, length = 2)
    private boolean codStatus;
    
    // Relacionamentos
    // @ManyToOne : Muitos p/ um
    // CascadeType : Define como as operaçoes de persistencia (INSERT, UPDATE, DELETE) serão
    //               propagadas para uma entidade filha no banco de dados.
    // MERGE: Propaga operaçoes de atualizaçoe de entidade pai para as filhas
    //        o merge é usado para atualizar uma entidade desaclopada no banco de dados.
    // 		  Se a entidade não existir no banco, o MERGE a insere.
    //        se ja existir, o MERGE atualiza os dados com os novos valores
    //	Além do MERGE, temos: ALL, PERSIST, REMOVE, REFRESH e DETACH
    // Fetch: Define como os dados relacionados serão carregados do banco de dados quando
    //	      a entidade for consultada 
    // FetchType.Laze: Os dados so serão carregados quando forem acessados explicitamente no código
    //FetchType.EAGLE: Join automaticos. os dados relacionados serão carregados quando a entidade for
    //		  consultada.
   
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = true)
    private Categoria categoria;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ItemPedido> ItemPedido;
}
