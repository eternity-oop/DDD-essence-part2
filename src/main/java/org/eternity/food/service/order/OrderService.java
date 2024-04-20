package org.eternity.food.service.order;

import org.eternity.food.domain.cart.Cart;
import org.eternity.food.domain.cart.CartRepository;
import org.eternity.food.domain.order.Order;
import org.eternity.food.domain.order.OrderRepository;
import org.eternity.food.domain.user.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class OrderService {
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    public OrderService(CartRepository cartRepository,
                        OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void place(UserId userId) {
        Cart cart= cartRepository.find(userId);
        Order order = cart.placeOrder();
        orderRepository.add(order);
    }

}
