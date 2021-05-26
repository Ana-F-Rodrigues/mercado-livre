package br.com.zupacadamy.anaflavia.mercadolivre.mercadolivre.usuarios.validacoes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;



public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(UniqueValue parametros) {
        domainAttribute = parametros.fieldName();
        klass = parametros.domainClass();
    }

    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext contexto) {
        Query q = em.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + "=:value" );
        q.setParameter("value", valor);
        List<?> listaResultados = q.getResultList();
        Assert.state(listaResultados.size() <= 1, "Foi encontrado(a) mais de um(a) " + klass.getName() + " com o atributo "+ domainAttribute);

        return listaResultados.isEmpty();
    }
}



