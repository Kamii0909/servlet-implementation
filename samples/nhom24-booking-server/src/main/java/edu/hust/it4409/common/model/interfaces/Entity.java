package edu.hust.it4409.common.model.interfaces;

import java.io.Serializable;

/**
 * A domain object that is specialized and globally identified in the current
 * domain.
 */
public interface Entity<T extends Serializable> extends IdentifiableDomainObject<T> {
    
}
