package org.eternity.food.persistence.order;

import org.eternity.base.jpa.BaseRepository;
import org.eternity.food.domain.order.Order;
import org.eternity.food.domain.order.OrderId;
import org.eternity.food.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
class DefaultOrderRepository extends BaseRepository<Order, OrderId, OrderJpaRepository> implements OrderRepository {
    public DefaultOrderRepository(OrderJpaRepository repository) {
        super(repository);
    }
}
