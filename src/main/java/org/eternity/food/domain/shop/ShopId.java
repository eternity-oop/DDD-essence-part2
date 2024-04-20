package org.eternity.food.domain.shop;

import org.eternity.base.domain.LongTypeIdentifier;
import org.eternity.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class ShopId extends LongTypeIdentifier {
    public ShopId(Long id) {
        super(id);
    }

    public static class ShopIdJavaType extends LongTypeIdentifierJavaType<ShopId> {
        public ShopIdJavaType() {
            super(ShopId.class);
        }
    }
}
