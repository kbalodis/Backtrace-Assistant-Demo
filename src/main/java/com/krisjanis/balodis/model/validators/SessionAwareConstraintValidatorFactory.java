package com.krisjanis.balodis.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
 
import org.hibernate.SessionFactory;
import org.hibernate.validator.internal.engine.ConstraintValidatorFactoryImpl;

public class SessionAwareConstraintValidatorFactory extends ConstraintValidatorFactoryImpl 
				implements ConstraintValidatorFactory {
	
	private SessionFactory sessionFactory;
	 
    public SessionAwareConstraintValidatorFactory() {
    }
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @SuppressWarnings("rawtypes")
	public <T extends ConstraintValidator<?, ?>> T getInstanceOf(Class<T> key) {
        T constraintValidator = super.getInstance( key );
        if ( constraintValidator instanceof SessionAwareConstraintValidator ) {
            ( ( SessionAwareConstraintValidator ) constraintValidator ).setSessionFactory( sessionFactory );
        }
        return constraintValidator;
    }
}
