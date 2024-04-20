package org.eternity.food;

import org.eternity.food.domain.cart.*;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.generic.time.TimePeriod;
import org.eternity.food.domain.order.*;
import org.eternity.food.domain.shop.*;
import org.eternity.food.domain.user.UserId;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import static org.assertj.core.util.Lists.list;

public class Fixtures {
    public static ShopId SHOP_ID = new ShopId(1L);
    public static MenuId MENU_ID = new MenuId(1L);
    public static OptionGroupId OPTION_GROUP_ID = new OptionGroupId(1L);
    public static CartId CART_ID = new CartId(1L);
    public static CartLineItemId CART_LINE_ITEM_ID = new CartLineItemId(1L);
    public static CartOptionGroupId CART_OPTION_GROUP_ID = new CartOptionGroupId(1L);
    public static OrderId ORDER_ID = new OrderId(1L);
    public static OrderLineItemId ORDER_LINE_ITEM_ID = new OrderLineItemId(1L);



    public static Shop.ShopBuilder aShop() {
        return Shop.builder()
                .id(SHOP_ID)
                .name("오겹돼지")
                .open(true)
                .minOrderPrice(Money.wons(13000))
                .operatingHours(new HashMap<>() {{
                    put(DayOfWeek.TUESDAY, TimePeriod.between(LocalTime.of(9, 0), LocalTime.of(22, 0)));
                }});
    }

    public static Menu.MenuBuilder aMenu() {
        return Menu.builder()
                .id(MENU_ID)
                .shopId(SHOP_ID)
                .name("삼겹살 1인세트")
                .description("삼겹살 + 야채세트 + 김치찌개")
                .open(true)
                .groups(list(anOptionGroup()
                        .name("기본")
                        .mandatory(true)
                        .options(list(anOption().name("소(250g)").price(Money.wons(12000)).build()))
                        .build()));
    }

    public static OptionGroup.OptionGroupBuilder anOptionGroup() {
        return OptionGroup.builder()
                .id(OPTION_GROUP_ID)
                .name("기본")
                .mandatory(true)
                .options(list(anOption().build()));
    }

    public static Option.OptionBuilder anOption() {
        return Option.builder()
                .name("소(250g)")
                .price(Money.wons(12000));
    }

    public static Cart.CartBuilder aCart() {
        return Cart.builder()
                .id(CART_ID)
                .userId(new UserId(1L))
                .shopId(new ShopId(1L))
                .items(list(aCartLineItem().build()));
    }

    public static CartLineItem.CartLineItemBuilder aCartLineItem() {
        return CartLineItem.builder()
                .id(CART_LINE_ITEM_ID)
                .menuId(MENU_ID)
                .menuName("삼겹살 1인세트")
                .count(1)
                .groups(list(aCartOptionGroup().build()));
    }

    public static CartOptionGroup.CartOptionGroupBuilder aCartOptionGroup() {
        return CartOptionGroup.builder()
                .id(CART_OPTION_GROUP_ID)
                .name("기본")
                .options(list(aCartOption().build()));
    }

    public static CartOption.CartOptionBuilder aCartOption() {
        return CartOption.builder()
                .name("소(250g)")
                .price(Money.wons(12000));
    }


    public static Order.OrderBuilder anOrder() {
        return Order.builder()
                .id(ORDER_ID)
                .userId(new UserId(1L))
                .shopId(new ShopId(1L))
                .orderedTime(LocalDateTime.of(2020, 1, 1, 12, 0))
                .items(list(anOrderLineItem().build()));
    }

    public static OrderLineItem.OrderLineItemBuilder anOrderLineItem() {
        return OrderLineItem.builder()
                .id(ORDER_LINE_ITEM_ID)
                .menuId(new MenuId(1L))
                .menuName("삼겹살 1인세트")
                .count(1)
                .groups(list(anOrderOptionGroup().build()));
    }

    public static OrderOptionGroup.OrderOptionGroupBuilder anOrderOptionGroup() {
        return OrderOptionGroup.builder()
                .name("기본")
                .options(list(anOrderOption().build()));
    }

    public static OrderOption.OrderOptionBuilder anOrderOption() {
        return OrderOption.builder()
                .name("소(250g)")
                .price(Money.wons(12000));
    }
}