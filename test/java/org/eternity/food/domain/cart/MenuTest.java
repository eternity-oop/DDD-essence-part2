package org.eternity.food.domain.cart;

import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.shop.Menu;
import org.junit.jupiter.api.Test;

import static org.assertj.core.util.Lists.list;
import static org.eternity.food.Fixtures.*;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    @Test
    public void 옵션그룹추가_중복이름_에러() {
        Menu menu = aMenu()
                        .groups(list(anOptionGroup().name("중복그룹").build()))
                        .build();
        try {
            menu.addOptionGroup(anOptionGroup().name("중복그룹").build());
            fail();
        }catch (IllegalArgumentException ex) {}
    }

    @Test
    public void 옵션그룹추가_필수갯수초과_에러() {
        Menu menu = aMenu()
                .groups(list(
                        anOptionGroup().mandatory(true).build(),
                        anOptionGroup().mandatory(true).build(),
                        anOptionGroup().mandatory(true).build()))
                .build();
        try {
            menu.addOptionGroup(anOptionGroup().mandatory(true).build());
            fail();
        }catch (IllegalArgumentException ex) {}
    }

    @Test
    public void 메뉴오픈_빈옵션그룹_에러() {
        Menu menu = aMenu().groups(list()).build();

        Throwable exception = assertThrows(IllegalStateException.class, () -> menu.open());

        assertEquals("옵션그룹은 하나 이상 존재해야 합니다.", exception.getMessage());
    }

    @Test
    public void 메뉴오픈_필수옵션그룹_없음_오류() {
        Menu menu = aMenu()
                        .groups(list(anOptionGroup().mandatory(false).build()))
                        .build();

        Throwable exception = assertThrows(IllegalStateException.class, () -> menu.open());

        assertEquals("필수 옵션그룹은 최소 1개는 등록되어 있어야 합니다.", exception.getMessage());
    }

    @Test
    public void 메뉴오픈_필수옵션그룹_갯수초과_오류() {
        Menu menu = aMenu()
                .groups(list(
                    anOptionGroup().name("그룹1").mandatory(true).build(),
                    anOptionGroup().name("그룹2").mandatory(true).build(),
                    anOptionGroup().name("그룹3").mandatory(true).build(),
                    anOptionGroup().name("그룹4").mandatory(true).build()))
                .build();

        Throwable exception = assertThrows(IllegalStateException.class, () -> menu.open());

        assertEquals("필수 옵션그룹은 3개까지만 등록가능 합니다.", exception.getMessage());
    }

    @Test
    public void 메뉴오픈_금액설정_옵션그룹_미존재_오류() {
        Menu menu = aMenu()
                .groups(list(
                        anOptionGroup().name("그룹1").mandatory(true).options(list(anOption().price(Money.ZERO).build())).build(),
                        anOptionGroup().name("그룹2").mandatory(true).options(list(anOption().price(Money.ZERO).build())).build()))
                .build();

        Throwable exception = assertThrows(IllegalStateException.class, () -> menu.open());

        assertEquals("금액이 설정된 옵션그룹이 최소 1개는 등록되어 있어야 합니다.", exception.getMessage());
    }
}
