package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.produtos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DetalhesProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String descricao;

	@ManyToOne
	private Produto produto; 

	public DetalhesProduto() {
	}

	public DetalhesProduto(String nome, String descricao, Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "DetalhesProduto [nome=" + nome + ", descricao=" + descricao + ", produto=" + produto + "]";
	}
	

}
