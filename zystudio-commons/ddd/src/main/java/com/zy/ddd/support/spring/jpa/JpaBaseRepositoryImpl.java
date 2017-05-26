package com.zy.ddd.support.spring.jpa;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author by zy.
 */
public class JpaBaseRepositoryImpl<T,ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements JpaBaseRepository<T,ID> {

    private final EntityManager em;

    public JpaBaseRepositoryImpl(JpaEntityInformation information, EntityManager em){
        super(information, em);
        this.em = em;
    }

    public JpaBaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    @Override
    public T valueOf(ID id) {
        return em.find(getDomainClass(), id);
    }
}