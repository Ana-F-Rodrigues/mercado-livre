package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.categorias;

import java.util.Optional;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.validacoes.UniqueValue;

public class CategoriaDto {

	    @NotBlank
	    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "O nome da categoria já existe")
	    private String nome;

	    @ManyToOne Long idCategoriaMae;

	    public CategoriaDto() {
	    }

	    public CategoriaDto(String nome) {
	        this.nome = nome;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public Long getIdCategoriaMae() {
	        return idCategoriaMae;
	    }

	    public void setIdCategoriaMae(Long idCategoria) {
	        this.idCategoriaMae = idCategoria;
	    }

	    public Categoria converter(CategoriaRepository categoriaRepository){

	        Categoria categoria = new Categoria(this.nome);
	        if(this.idCategoriaMae != null){
	            Optional<Categoria> categoriaMae = categoriaRepository.findById(this.idCategoriaMae);
	            if(categoriaMae.isPresent()) {
	                categoria.setCategoriaMae(categoriaMae.get());
	            }
	        }
	        return categoria;
	    }
}
