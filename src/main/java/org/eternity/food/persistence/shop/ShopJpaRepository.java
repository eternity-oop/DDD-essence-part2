package org.eternity.food.persistence.shop;

import org.eternity.food.domain.shop.Shop;
import org.eternity.food.domain.shop.ShopId;
import org.springframework.data.jpa.repository.JpaRepository;

interface ShopJpaRepository extends JpaRepository<Shop, ShopId> {
}
