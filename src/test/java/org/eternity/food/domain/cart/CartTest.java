package org.eternity.food.domain.cart;

import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.order.Order;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.eternity.food.Fixtures.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {
    @Test
    public void 장바구니_총합_계산() {
        Cart cart = aCart().items(asList(
                            aCartLineItem()
                                .groups(asList(
                                    aCartOptionGroup()
                                        .options(asList(
                                            aCartOption().price(Money.wons(10000L)).build(),
                                            aCartOption().price(Money.wons(20000L)).build()))
                                        .build()))
                            .build()))
                        .build();

        assertEquals(Money.wons(30000L), cart.getTotalPrice());
    }

    @Test
    public void 주문() {
        Cart cart = aCart().items(asList(
                        aCartLineItem()
                                .groups(asList(
                                        aCartOptionGroup()
                                                .options(asList(
                                                        aCartOption().price(Money.wons(10000L)).build(),
                                                        aCartOption().price(Money.wons(20000L)).build()))
                                                .build()))
                                .build()))
                .build();
        Order order = cart.placeOrder();

        assertThat(order.getPrice()).isEqualTo(Money.wons(30000L));
        assertThat(order.getPrice()).isEqualTo(cart.getTotalPrice());
    }
}
