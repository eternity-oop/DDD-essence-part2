package org.eternity.food.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import org.eternity.base.domain.ValueObject;
import org.eternity.food.domain.generic.money.Money;

@Embeddable
@Getter
public class OrderOption extends ValueObject<OrderOption> {
    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private Money price;

    @Builder
    public OrderOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    OrderOption() {
    }

    @Override
    protected Object[] getEqualityFields() {
        return new Object[] { name, price };
    }
}
