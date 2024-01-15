package edu.hust.it4409.common.model.interfaces;

/**
 * A domain object with its identification proved by a single attribute, its Id.
 */
public interface IdentifiableDomainObject<T> extends DomainObject {
    T getId();
}
