package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.categorias.Categoria;
import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.categorias.CategoriaRepository;

public class ProdutoDto {

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal preco;

	@NotNull
	@PositiveOrZero
	private Integer quantidade;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	//@Size(min = 3)
	private List<DetalheProdutoDto> detalheProdutoDto = new ArrayList<>(); 
																												// produto
																												// pode
																												// ter
																												// várias
																												// características

	@NotNull
	//@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	public ProdutoDto() {
	}

	public List<DetalheProdutoDto> getCaracteristicas() {
		return detalheProdutoDto ;
	}

	public void setCaracteristicas(List<DetalheProdutoDto> caracteristicas) {
		this.detalheProdutoDto = caracteristicas;
	}

	public ProdutoDto(String nome, BigDecimal preco, Integer quantidade, String descricao,
			List<DetalheProdutoDto> detalheProdutoDto, Long idCategoria) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.detalheProdutoDto = detalheProdutoDto;
		this.idCategoria = idCategoria;
	}

	public Produto converter(CategoriaRepository categoriaRepository) {
		Optional<Categoria> categoriaOp = categoriaRepository.findById(this.idCategoria);
																						

		if (categoriaOp.isPresent()) {
			return new Produto(nome, preco, quantidade, descricao, detalheProdutoDto,
					categoriaOp.get());
		}

		return null;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<DetalheProdutoDto> getCaracteristicasProdutoDTORequestList() {
		return detalheProdutoDto;
	}

	public void setCaracteristicasProdutoDTORequestList(
			List<DetalheProdutoDto> detalheProdutoDto) {
		this.detalheProdutoDto = detalheProdutoDto;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "ProdutoDTORequest{" + "nome='" + nome + '\'' + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", descricao='" + descricao + '\'' + ", caracteristicasProdutoDTORequestList="
				+ detalheProdutoDto + ", idCategoria=" + idCategoria + '}';
	}

}
