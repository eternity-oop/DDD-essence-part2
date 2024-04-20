package org.eternity.food.domain.user;

import org.eternity.base.domain.LongTypeIdentifier;
import org.eternity.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class UserId extends LongTypeIdentifier {
    public UserId(Long id) {
        super(id);
    }

    public static class UserIdJavaType extends LongTypeIdentifierJavaType<UserId> {
        public UserIdJavaType() {
            super(UserId.class);
        }
    }
}
