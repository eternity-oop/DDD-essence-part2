package org.eternity.food.domain.shop;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import org.eternity.base.domain.ValueObject;
import org.eternity.food.domain.generic.money.Money;

@Embeddable
@Getter
public class Option extends ValueObject<Option> {
    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private Money price;

    @Builder
    public Option(String name, Money price) {
        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("옵션명은 2글자 이상이어야 합니다.");
        }

        if (price == null) {
            throw new NullPointerException("옵션 가격은 null이어서는 안됩니다.");
        }

        this.name = name;
        this.price = price;
    }

    Option() {
    }

    public boolean isFree() {
        return Money.ZERO.equals(price);
    }

    public Option changeName(String name) {
        return new Option(name, this.price);
    }

    protected Object[] getEqualityFields() {
        return new Object[] { name, price };
    }
}
