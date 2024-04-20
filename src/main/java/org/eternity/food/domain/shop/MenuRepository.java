package org.eternity.food.domain.shop;

import org.eternity.base.domain.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MenuRepository extends Repository<Menu, MenuId> {
    List<Menu> findOpenMenusIn(ShopId shopId);
    List<Menu> find(ShopId shopId);
}
