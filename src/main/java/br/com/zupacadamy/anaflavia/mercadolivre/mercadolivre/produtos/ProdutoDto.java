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
import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.compartilhado.ExisteId;

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

	public ProdutoDto(@NotBlank String nome, @NotNull @Positive BigDecimal preco,
			@NotNull @PositiveOrZero Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
			List<DetalheProdutoDto> caracteristicas, @NotNull Long idCategoria) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
		this.idCategoria = idCategoria;
	}

	 @Size(min = 3)
	private List<DetalheProdutoDto> caracteristicas = new ArrayList<>();

	@NotNull
    @ExisteId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	public Produto converter(CategoriaRepository categoriaRepository) {
		Optional<Categoria> categoriaOp = categoriaRepository.findById(this.idCategoria);

		if (categoriaOp.isPresent()) {
			return new Produto(nome, preco, quantidade, descricao, caracteristicas, categoriaOp.get());
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

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

}
