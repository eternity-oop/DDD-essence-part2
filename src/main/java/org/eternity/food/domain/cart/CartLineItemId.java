package org.eternity.food.domain.cart;

import org.eternity.base.domain.LongTypeIdentifier;
import org.eternity.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class CartLineItemId extends LongTypeIdentifier {
    public CartLineItemId(Long id) {
        super(id);
    }

    public static class CartLineItemIdJavaType extends LongTypeIdentifierJavaType<CartLineItemId> {
        public CartLineItemIdJavaType() {
            super(CartLineItemId.class);
        }
    }
}
