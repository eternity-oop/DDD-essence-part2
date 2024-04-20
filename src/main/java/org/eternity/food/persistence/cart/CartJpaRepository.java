package org.eternity.food.persistence.cart;

import jakarta.persistence.LockModeType;
import org.eternity.food.domain.cart.Cart;
import org.eternity.food.domain.cart.CartId;
import org.eternity.food.domain.user.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface CartJpaRepository extends JpaRepository<Cart, CartId> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Cart findByUserId(UserId userId);
}
