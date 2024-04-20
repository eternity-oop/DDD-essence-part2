package org.eternity.food.domain.shop;

import org.eternity.base.domain.LongTypeIdentifier;
import org.eternity.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class OptionGroupId extends LongTypeIdentifier {
    public OptionGroupId(Long id) {
        super(id);
    }

    public static class MenuOptionGroupIdJavaType extends LongTypeIdentifierJavaType<OptionGroupId> {
        public MenuOptionGroupIdJavaType() {
            super(OptionGroupId.class);
        }
    }
}
