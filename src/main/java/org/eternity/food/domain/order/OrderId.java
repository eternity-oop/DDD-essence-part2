package org.eternity.food.domain.order;

import org.eternity.base.domain.LongTypeIdentifier;
import org.eternity.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class OrderId extends LongTypeIdentifier {
    public OrderId(Long id) {
        super(id);
    }

    public static class OrderIdJavaType extends LongTypeIdentifierJavaType<OrderId> {
        public OrderIdJavaType() {
            super(OrderId.class);
        }
    }
}
