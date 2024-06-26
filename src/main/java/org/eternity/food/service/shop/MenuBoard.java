package org.eternity.food.service.shop;

import lombok.Data;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.shop.Menu;
import org.eternity.food.domain.shop.Shop;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MenuBoard {
    private Long shopId;
    private String shopName;
    private boolean open;
    private Money minOrderAmount;
    private List<MenuItem> menuItems;

    public MenuBoard(Shop shop, List<Menu> menus) {
        this.shopId = shop.getId().longValue();
        this.shopName = shop.getName();
        this.open = shop.isOpen();
        this.minOrderAmount = shop.getMinOrderPrice();
        this.menuItems = toMenuItems(menus);
    }

    private List<MenuItem> toMenuItems(List<Menu> menus) {
        return menus.stream().map(MenuItem::new).collect(Collectors.toList());
    }

    @Data
    public static class MenuItem {
        private Long menuId;
        private String menuName;
        private Money menuBasePrice;
        private String menuDescription;

        public MenuItem(Menu menu) {
            this.menuId = menu.getId().longValue();
            this.menuName = menu.getName();
            this.menuBasePrice = Money.ZERO;
            this.menuDescription = menu.getDescription();
        }
    }
}
