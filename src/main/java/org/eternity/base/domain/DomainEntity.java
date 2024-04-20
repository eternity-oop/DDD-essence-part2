package org.eternity.base.domain;

public abstract class DomainEntity<T extends DomainEntity<T, TID>, TID> {
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        return equals((T)other);
    }

    public boolean equals(T other) {
        if (other == null) {
            return false;
        }

        if (getId() == null) {
            return false;
        }

        if (other.getClass().equals(getClass())) {
            return getId().equals(other.getId());
        }

        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId().hashCode();
    }

    abstract public TID getId();
}