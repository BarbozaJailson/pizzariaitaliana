package com.itb.mif3an.pizzariaitaliana.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Admin.class, name = "ADMIN"),
		@JsonSubTypes.Type(value = Cliente.class, name = "CLIENTE"),
		@JsonSubTypes.Type(value = Funcionario.class, name = "FUNCIONARIO"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "nome",nullable = false )
    private String nome;
	@Column(name = "email", nullable = false)
    private String email;
	@Column(name = "password", nullable = false, length = 255)
    private String password;
	@Column(name = "logradouro", nullable = true, length = 100)
    private String logradouro;
	@Column(name = "cpf", nullable = true, length = 15)
    private String cpf;
	@Column(name = "bairro", nullable = true, length = 45)
    private String bairro;
	@Column(name = "cidade", nullable = true, length = 45)
    private String cidade;
	@Column(name = "cep", nullable = true)
    private String cep;
	@Column(name = "uf", nullable = true, length = 2)
    private String uf;
	@Column(name = "cod_status", nullable = false)
    private boolean codStatus;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Telefone> telefones;


	// insertable = false -> o valor desse campo não será considerado na hora de inserir um novo registro
	// updatable = false -> o valor desse campo não será atualizado quando huver update
	@Enumerated(EnumType.STRING)
	@Column(insertable = false, updatable = false)
	private Rule role;
}