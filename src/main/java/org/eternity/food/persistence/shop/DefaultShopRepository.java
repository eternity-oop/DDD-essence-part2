package org.eternity.food.persistence.shop;

import org.eternity.base.jpa.BaseRepository;
import org.eternity.food.domain.shop.Shop;
import org.eternity.food.domain.shop.ShopId;
import org.eternity.food.domain.shop.ShopRepository;
import org.springframework.stereotype.Repository;

@Repository
class DefaultShopRepository extends BaseRepository<Shop, ShopId, ShopJpaRepository> implements ShopRepository {
    public DefaultShopRepository(ShopJpaRepository repository) {
        super(repository);
    }
}
