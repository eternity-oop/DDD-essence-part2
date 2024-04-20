package org.eternity.food.persistence.shop;

import jakarta.persistence.LockModeType;
import org.eternity.food.domain.shop.Menu;
import org.eternity.food.domain.shop.MenuId;
import org.eternity.food.domain.shop.ShopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

interface MenuJpaRepository extends JpaRepository<Menu, MenuId> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    List<Menu> findByShopIdAndOpenIsTrue(ShopId shopId);

    List<Menu> findByShopId(ShopId shopId);
}
