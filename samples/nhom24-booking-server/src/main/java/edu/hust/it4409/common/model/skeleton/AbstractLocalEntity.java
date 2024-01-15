package edu.hust.it4409.common.model.skeleton;

import edu.hust.it4409.common.model.interfaces.LocalEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@MappedSuperclass
public abstract class AbstractLocalEntity<T extends AbstractAggregateRoot> implements LocalEntity<Long, T> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    protected AbstractLocalEntity() {
        // For Hibernate
    }
    
    protected AbstractLocalEntity(Long id) {
        this.id = id;
    }
    
    
    @Override
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    protected abstract void setRoot(T root);
    
}
