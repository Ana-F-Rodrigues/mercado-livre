package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.compartilhado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;



public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(ExisteId parametros) {
        domainAttribute = parametros.fieldName();
        klass = parametros.domainClass();
    }

    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext contexto) {
    	if(valor == null) {
    		return true;
    	}
    	
    	
        Query q = em.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + "=:value" );
        q.setParameter("value", valor);
        List<?> listaResultados = q.getResultList();
        
        return !listaResultados.isEmpty();
    }
}



