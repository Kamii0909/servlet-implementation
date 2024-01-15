package edu.hust.it4409.common.model.skeleton;

import edu.hust.it4409.common.model.interfaces.AggregateRoot;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
public abstract class AbstractAggregateRoot implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected AbstractAggregateRoot() {
        // For Hibernate
    }
    
    protected AbstractAggregateRoot(Long id) {
        this.id = id;
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
}
