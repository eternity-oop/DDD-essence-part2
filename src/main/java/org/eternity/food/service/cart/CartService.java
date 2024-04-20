package org.eternity.food.service.cart;

import org.eternity.food.domain.cart.Cart;
import org.eternity.food.domain.cart.CartLineItem;
import org.eternity.food.domain.cart.CartRepository;
import org.eternity.food.domain.shop.Shop;
import org.eternity.food.domain.shop.ShopRepository;
import org.eternity.food.domain.user.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    private CartRepository cartRepository;
    private ShopRepository shopRepository;
    private CartLineItemMapper cartLineItemMapper;

    public CartService(CartRepository cartRepository, ShopRepository shopRepository, CartLineItemMapper cartLineItemMapper) {
        this.cartRepository = cartRepository;
        this.shopRepository = shopRepository;
        this.cartLineItemMapper = cartLineItemMapper;
    }

    @Transactional
    public void addCartLineItem(UserId userId, CartLineItemRequest cartLineItemRequest) {
        Cart cart = cartRepository.find(userId);

        if (cart == null) {
            throw new IllegalStateException("'" + userId.longValue() + "'의 카트는 존재하지 않는 사용자 ID입니다.");
        }

        Shop shop = shopRepository.find(cartLineItemRequest.getShopId());
        CartLineItem cartLineItem = cartLineItemMapper.map(cartLineItemRequest);

        cart.addItem(shop, cartLineItem);
    }
}
