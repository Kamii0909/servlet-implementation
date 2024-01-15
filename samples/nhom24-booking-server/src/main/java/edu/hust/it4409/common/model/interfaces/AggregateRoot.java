package edu.hust.it4409.common.model.interfaces;

import java.io.Serializable;

/**
 * The root of an aggregate. Responsible for the scope encapsulation of its
 * local domain entities.
 */
public interface AggregateRoot<T extends Serializable> extends Entity<T> {
    
}
