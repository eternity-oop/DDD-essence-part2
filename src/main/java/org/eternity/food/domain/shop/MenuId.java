package org.eternity.food.domain.shop;

import org.eternity.base.domain.LongTypeIdentifier;
import org.eternity.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class MenuId extends LongTypeIdentifier {
    public MenuId(Long id) {
        super(id);
    }

    public static class MenuIdJavaType extends LongTypeIdentifierJavaType<MenuId> {
        public MenuIdJavaType() {
            super(MenuId.class);
        }
    }
}
