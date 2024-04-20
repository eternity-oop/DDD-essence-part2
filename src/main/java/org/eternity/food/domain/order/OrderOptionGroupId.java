package org.eternity.food.domain.order;

import org.eternity.base.domain.LongTypeIdentifier;
import org.eternity.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class OrderOptionGroupId extends LongTypeIdentifier {
    public OrderOptionGroupId(Long id) {
        super(id);
    }

    public static class OrderOptionGroupIdJavaType extends LongTypeIdentifierJavaType<OrderOptionGroupId> {
        public OrderOptionGroupIdJavaType() {
            super(OrderOptionGroupId.class);
        }
    }
}
