package org.eternity.food.persistence.cart;

import org.eternity.base.jpa.BaseRepository;
import org.eternity.food.domain.cart.Cart;
import org.eternity.food.domain.cart.CartId;
import org.eternity.food.domain.cart.CartRepository;
import org.eternity.food.domain.user.UserId;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
class CartRepositoryImpl extends BaseRepository<Cart, CartId, CartJpaRepository> implements CartRepository {
    public CartRepositoryImpl(CartJpaRepository repository) {
        super(repository);
    }

    @Transactional
    public Cart find(UserId userId) {
        return repository.findByUserId(userId);
    }
}
