package org.eternity.food.domain.order;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.eternity.base.domain.DomainEntity;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.order.OrderOptionGroupId.OrderOptionGroupIdJavaType;
import org.hibernate.annotations.JavaType;

import java.util.List;

@Entity
@Getter
public class OrderOptionGroup extends DomainEntity<OrderOptionGroup, OrderOptionGroupId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JavaType(OrderOptionGroupIdJavaType.class)
    private OrderOptionGroupId id;

    @Column(name="NAME")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="ORDER_OPTION", joinColumns = @JoinColumn(name="ORDER_OPTION_GROUP_ID"))
    private List<OrderOption> options;

    public OrderOptionGroup(String name, List<OrderOption> options) {
        this(null, name, options);
    }

    @Builder
    OrderOptionGroup(OrderOptionGroupId id, String name, List<OrderOption> options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }

    OrderOptionGroup() {
    }

    public Money getPrice() {
        return options.stream().map(option -> option.getPrice()).reduce(Money.ZERO, Money::plus);
    }
}
