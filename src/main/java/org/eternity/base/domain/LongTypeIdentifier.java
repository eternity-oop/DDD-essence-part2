package org.eternity.base.domain;


public abstract class LongTypeIdentifier extends ValueObject<LongTypeIdentifier> {
    private Long id;

    public LongTypeIdentifier(Long id) {
        this.id = id;
    }

    public Long longValue() {
        return id;
    }

    public Long nextValue() {
        return id + 1;
    }

    @Override
    protected Object[] getEqualityFields() {
        return new Object[] { id };
    }
}
