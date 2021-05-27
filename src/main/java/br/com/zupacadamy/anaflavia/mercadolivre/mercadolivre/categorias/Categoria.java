package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.categorias;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Categoria {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String nome;
	    
	    @ManyToOne 
	    private Categoria categoriaMae;
	    
	    public Categoria() {
	    }
	    
	    public Categoria(String nome) { 
	        this.nome = nome;
	    }
	    
	    public void setCategoriaMae(Categoria categoriaMae) { 
	        this.categoriaMae = categoriaMae;
	    }
	    
	    @Override
	    public String toString() {
	        return "Categoria{" +
	                "id=" + id +
	                ", nome='" + nome + '\'' +
	                ", categoriaMae=" + categoriaMae +
	                '}';
	    }

}
