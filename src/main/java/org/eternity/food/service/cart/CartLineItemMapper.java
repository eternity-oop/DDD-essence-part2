package org.eternity.food.service.cart;

import org.eternity.food.domain.cart.CartLineItem;
import org.eternity.food.domain.cart.CartOption;
import org.eternity.food.domain.cart.CartOptionGroup;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.shop.Menu;
import org.eternity.food.domain.shop.MenuRepository;
import org.eternity.food.domain.shop.Option;
import org.eternity.food.domain.shop.OptionGroup;
import org.eternity.food.service.cart.CartLineItemRequest.CartOptionGroupRequest;
import org.eternity.food.service.cart.CartLineItemRequest.CartOptionRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartLineItemMapper {
    private MenuRepository menuRepository;

    public CartLineItemMapper(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public CartLineItem map(CartLineItemRequest request) {
        Menu menu = menuRepository.find(request.getMenuId());

        CartLineItem cartLineItem = new CartLineItem(menu.getId(), menu.getName(), request.getCount());

        for(CartOptionGroupRequest optionGroupRequest : request.getOptionGroupRequests()) {
            OptionGroup optionGroup = menu.getOptionGroup(optionGroupRequest.getOptionGroupId()).orElseThrow(() -> new IllegalArgumentException());

            List<CartOption> cartOptions = new ArrayList<>();
            for(CartOptionRequest optionRequest : optionGroupRequest.getOptions()) {
                Option found = optionGroup
                                    .findOption(new Option(optionRequest.getName(),
                                                           Money.wons(optionRequest.getPrice())))
                                    .orElseThrow(() -> new IllegalArgumentException());
                cartOptions.add(new CartOption(found.getName(), found.getPrice()));
            }

            cartLineItem.addCartOptionGroup(new CartOptionGroup(optionGroup.getName(), cartOptions));
        }

        return cartLineItem;
    }
}
