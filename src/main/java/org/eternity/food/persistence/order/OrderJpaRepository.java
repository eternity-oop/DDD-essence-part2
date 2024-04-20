package org.eternity.food.persistence.order;

import org.eternity.food.domain.order.Order;
import org.eternity.food.domain.order.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderJpaRepository extends JpaRepository<Order, OrderId> {
}
