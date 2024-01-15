package edu.hust.it4409.common.model.interfaces;

import java.io.Serializable;

/**
 * A local entity is identified by its parent (aggregate root) and is globally
 * identified by its parent. 2 Local entites with the same Id and different
 * parent is 2 different entities. For pratical purpose, Local entity ID can be
 * unique in the whole domain.
 */
public interface LocalEntity<T extends Serializable, K extends AggregateRoot<?>> extends Entity<T> {
    K getAggregateRoot();
}
