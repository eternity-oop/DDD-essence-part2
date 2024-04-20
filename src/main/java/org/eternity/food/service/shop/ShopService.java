package org.eternity.food.service.shop;

import org.eternity.food.domain.shop.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopService {
    private ShopRepository shopRepository;
    private MenuRepository menuRepository;

    public ShopService(ShopRepository shopRepository, MenuRepository menuRepository) {
        this.shopRepository = shopRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional(readOnly = true)
    public MenuBoard getMenuBoard(ShopId shopId) {
        Shop shop = shopRepository.find(shopId);
        List<Menu> menus = menuRepository.find(shopId);

        return new MenuBoard(shop, menus);
    }
}
