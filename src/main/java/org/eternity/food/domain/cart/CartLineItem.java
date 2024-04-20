package org.eternity.food.domain.cart;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.eternity.base.domain.DomainEntity;
import org.eternity.food.domain.cart.CartLineItemId.CartLineItemIdJavaType;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.order.OrderLineItem;
import org.eternity.food.domain.order.OrderOptionGroup;
import org.eternity.food.domain.shop.Menu;
import org.eternity.food.domain.shop.MenuId;
import org.eternity.food.domain.shop.MenuId.MenuIdJavaType;
import org.hibernate.annotations.JavaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
public class CartLineItem extends DomainEntity<CartLineItem, CartLineItemId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JavaType(CartLineItemIdJavaType.class)
    private CartLineItemId id;

    @Column(name="MENU_ID")
    @JavaType(MenuIdJavaType.class)
    private MenuId menuId;

    @Column(name="MENU_NAME")
    private String menuName;

    @Column(name="MENU_COUNT")
    private int menuCount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="CART_LINE_ITEM_ID")
    private List<CartOptionGroup> groups = new ArrayList<>();

    public CartLineItem(Menu menu, int count, CartOptionGroup ... groups) {
        this(null, menu.getId(), menu.getName(), count, Arrays.asList(groups));
    }

    public CartLineItem(MenuId menuId, String menuName, int count, CartOptionGroup ... groups) {
        this(null, menuId, menuName, count, Arrays.asList(groups));
    }

    @Builder
    public CartLineItem(CartLineItemId id, MenuId menuId, String menuName, int count, List<CartOptionGroup> groups) {
        this.id = id;
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuCount = count;
        this.groups.addAll(groups);
    }

    CartLineItem() {
    }

    public void combine(CartLineItem other) {
        this.menuCount += other.getMenuCount();
    }

    public void addCartOptionGroup(CartOptionGroup cartOptionGroup) {
        groups.add(cartOptionGroup);
    }

    public boolean equalsNested(CartLineItem other) {
        if (!this.menuId.equals(other.getMenuId())) {
            return false;
        }

        for(CartOptionGroup thisGroup : groups) {
            if (!other.getGroups().stream().anyMatch(thatGroup -> thatGroup.equalsNested(thisGroup))) {
                return false;
            }
        }

        return true;
    }

    public Money getTotalPrice() {
        return groups.stream()
                .map(group -> group.getTotalPrice())
                .reduce(Money.ZERO, (first, second) -> first.plus(second))
                .times(menuCount);
    }

    OrderLineItem toOrderLineItem() {
        return new OrderLineItem(menuId, menuName, menuCount, toOrderOptionGroups());
    }

    private List<OrderOptionGroup> toOrderOptionGroups() {
        return groups.stream().map(CartOptionGroup::toOrderOptionGroup).collect(Collectors.toList());
    }
}
