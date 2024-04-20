package org.eternity.base.domain;

public interface Repository<AR extends AggregateRoot<AR, ARID>, ARID> {
    void add(AR root);
    AR find(ARID id);
    void remove(ARID id);
    void remove(AR root);
}
