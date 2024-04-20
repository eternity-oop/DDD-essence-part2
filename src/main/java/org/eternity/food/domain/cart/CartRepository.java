package org.eternity.food.domain.cart;

import org.eternity.base.domain.Repository;
import org.eternity.food.domain.user.UserId;

public interface CartRepository extends Repository<Cart, CartId> {
    Cart find(UserId userId);
}
