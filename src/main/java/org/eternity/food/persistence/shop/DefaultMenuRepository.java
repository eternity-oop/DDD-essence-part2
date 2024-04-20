package org.eternity.food.persistence.shop;

import org.eternity.base.jpa.BaseRepository;
import org.eternity.food.domain.shop.Menu;
import org.eternity.food.domain.shop.MenuId;
import org.eternity.food.domain.shop.MenuRepository;
import org.eternity.food.domain.shop.ShopId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class DefaultMenuRepository extends BaseRepository<Menu, MenuId, MenuJpaRepository> implements MenuRepository {
    public DefaultMenuRepository(MenuJpaRepository repository) {
        super(repository);
    }

    @Override
    public List<Menu> findOpenMenusIn(ShopId shopId) {
        return repository.findByShopIdAndOpenIsTrue(shopId);
    }

    @Override
    public List<Menu> find(ShopId shopId) {
        return repository.findByShopId(shopId);
    }
}
