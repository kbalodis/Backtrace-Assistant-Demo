package com.krisjanis.balodis.model.validators;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.krisjanis.balodis.service.BacktraceService;



public class UniqueValidator implements ConstraintValidator<UniqueField, String> {
	
    private BacktraceService backtraceService;

    private Class<?> entityClass;
    private String uniqueField;
    
    @SuppressWarnings("unchecked")
	public void initialize(UniqueField unique) {
        this.entityClass = (Class<Object>)unique.entity();
        this.uniqueField = unique.property();
    }

    public boolean isValid(String value, ConstraintValidatorContext cvContext) {
    	
    	boolean result = backtraceService.strObjParm(value, this.entityClass, this.uniqueField);
    	return result;
    	
    }

}


