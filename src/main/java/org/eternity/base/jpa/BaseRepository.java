package org.eternity.base.jpa;

import org.eternity.base.domain.AggregateRoot;
import org.eternity.base.domain.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseRepository<AR extends AggregateRoot<AR, ARID>, ARID, R extends JpaRepository<AR, ARID>> implements Repository<AR , ARID> {
    protected R repository;

    public BaseRepository(R repository) {
        this.repository = repository;
    }

    public void add(AR root) {
        repository.save(root);
    }

    public AR find(ARID id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(ARID id) {
        repository.deleteById(id);
    }

    public void remove(AR root) {
        repository.delete(root);
    }
}
