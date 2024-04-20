package org.eternity.food.domain.cart;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.eternity.base.domain.DomainEntity;
import org.eternity.food.domain.cart.CartOptionGroupId.CartOptionGroupIdJavaType;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.order.OrderOption;
import org.eternity.food.domain.order.OrderOptionGroup;
import org.hibernate.annotations.JavaType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
public class CartOptionGroup extends DomainEntity<CartOptionGroup, CartOptionGroupId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JavaType(CartOptionGroupIdJavaType.class)
    private CartOptionGroupId id;

    @Column(name="NAME")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="CART_OPTION", joinColumns = @JoinColumn(name="CART_OPTION_GROUP_ID"))
    private List<CartOption> options;

    public CartOptionGroup(String name, CartOption ... options) {
        this(name, Arrays.asList(options));
    }

    public CartOptionGroup(String name, List<CartOption> options) {
        this(null, name, options);
    }

    @Builder
    public CartOptionGroup(CartOptionGroupId id, String name, List<CartOption> options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }

    CartOptionGroup() {
    }

    public boolean equalsNested(CartOptionGroup other) {
        if (!name.equals(other.getName())) {
            return false;
        }

        for(CartOption thisOption : options) {
            if (!other.getOptions().stream().anyMatch(thatOption -> thatOption.equals(thisOption))) {
                return false;
            }
        }

        return true;
    }

    public Money getTotalPrice() {
        return options.stream().map(option -> option.getPrice()).reduce(Money.ZERO, (first, second) -> first.plus(second));
    }

    OrderOptionGroup toOrderOptionGroup() {
        return new OrderOptionGroup(name, toOrderOptions());
    }

    private List<OrderOption> toOrderOptions() {
        return options.stream().map(CartOption::toOrderOption).collect(Collectors.toList());
    }
}
