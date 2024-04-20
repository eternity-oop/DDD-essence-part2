package org.eternity.food.service.cart;

import lombok.Data;
import org.eternity.food.domain.cart.CartId;
import org.eternity.food.domain.shop.MenuId;
import org.eternity.food.domain.shop.OptionGroupId;
import org.eternity.food.domain.shop.ShopId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class CartLineItemRequest {
    private ShopId shopId;
    private CartId cartId;
    private MenuId menuId;
    private Integer count;
    private List<CartOptionGroupRequest> optionGroupRequests;

    public CartLineItemRequest() {
    }

    public CartLineItemRequest(ShopId shopId, CartId cartId, MenuId menuId, Integer count, CartOptionGroupRequest ... optionGroupRequests) {
        this.shopId = shopId;
        this.cartId = cartId;
        this.menuId = menuId;
        this.count = count;
        this.optionGroupRequests = Arrays.asList(optionGroupRequests);
    }

    @Data
    public static class CartOptionGroupRequest {
        private OptionGroupId optionGroupId;
        private Integer count;
        private List<String> optionNames = new ArrayList<>();

        public CartOptionGroupRequest() {
        }

        public CartOptionGroupRequest(OptionGroupId optionGroupId, Integer count, List<String> optionNames) {
            this.optionGroupId = optionGroupId;
            this.count = count;
            this.optionNames = optionNames;
        }
    }
}
