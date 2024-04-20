package org.eternity.food.domain.order;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.eternity.base.domain.AggregateRoot;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.order.OrderId.OrderIdJavaType;
import org.eternity.food.domain.shop.ShopId;
import org.eternity.food.domain.shop.ShopId.ShopIdJavaType;
import org.eternity.food.domain.user.UserId;
import org.eternity.food.domain.user.UserId.UserIdJavaType;
import org.hibernate.annotations.JavaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="ORDERS")
@Getter
public class Order extends AggregateRoot<Order, OrderId> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JavaType(OrderIdJavaType.class)
    private OrderId id;

    @Column(name="USER_ID")
    @JavaType(UserIdJavaType.class)
    private UserId userId;

    @Column(name="SHOP_ID")
    @JavaType(ShopIdJavaType.class)
    private ShopId shopId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="ORDER_ID")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name="ORDERED_TIME")
    private LocalDateTime orderedTime;

    public Order(UserId userId, ShopId shopId, List<OrderLineItem> items) {
        this(null, userId, shopId, items, LocalDateTime.now());
    }

    @Builder
    public Order(OrderId id, UserId userId, ShopId shopId, List<OrderLineItem> items, LocalDateTime orderedTime) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderLineItems = items;
    }

    Order() {
    }

    public Money getPrice() {
        return orderLineItems.stream().map(orderLineItem -> orderLineItem.getPrice()).reduce(Money.ZERO, Money::plus);
    }
}
