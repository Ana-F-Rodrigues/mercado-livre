package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.produtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.categorias.Categoria;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	private BigDecimal preco;
	private Integer quantidade;
	private String descricao;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<DetalhesProduto> caracteristicasProduto = new ArrayList<DetalhesProduto>();
	@ManyToOne
	private Categoria categoria;
	private LocalDateTime instanteCadastro;

	public Produto() {
	}

	public Produto(@NotBlank String nome, BigDecimal preco, Integer quantidade, String descricao,
			List<DetalheProdutoDto> caracteristicasProduto, Categoria categoria){
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.caracteristicasProduto = caracteristicasProduto.stream().map(detalhesProdutoDto ->  detalhesProdutoDto.converter(this)
				).collect(Collectors.toList());
			
		this.categoria = categoria;
		this.instanteCadastro = LocalDateTime.now();
		
		
	}
	
	
	

//	public Produto(String nome, BigDecimal preco, Integer quantidade, String descricao,
//			List<DetalheProdutoDto> caracteristicasProduto, Categoria categoria) {
//		this.nome = nome;
//		this.preco = preco;
//		this.quantidade = quantidade;
//		this.descricao = descricao;
//
//		this.caracteristicasProduto = caracteristicasProduto.stream().map(
//				detalhesProdutoDto ->  detalhesProdutoDto.converter(this)
//				).collect(Collectors.toList());
//		this.categoria = categoria;
//		this.instanteCadastro = LocalDateTime.now();
//
//	
//		Assert.isTrue((this.preco.compareTo(BigDecimal.ZERO)) == 1, "O preço precisa ser maior que R$ 0,00");
//		Assert.isTrue(this.quantidade >= 0, "A quantidade de itens disponíveis precisa ser maior ou igual a zero");
//	}
}
