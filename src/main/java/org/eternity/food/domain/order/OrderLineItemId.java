package org.eternity.food.domain.order;

import org.eternity.base.domain.LongTypeIdentifier;
import org.eternity.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class OrderLineItemId extends LongTypeIdentifier {
    public OrderLineItemId(Long id) {
        super(id);
    }

    public static class OrderLineItemIdJavaType extends LongTypeIdentifierJavaType<OrderLineItemId> {
        public OrderLineItemIdJavaType() {
            super(OrderLineItemId.class);
        }
    }
}
