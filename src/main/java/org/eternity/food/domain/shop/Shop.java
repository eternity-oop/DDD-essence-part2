package org.eternity.food.domain.shop;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.eternity.base.domain.AggregateRoot;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.generic.time.TimePeriod;
import org.eternity.food.domain.shop.ShopId.ShopIdJavaType;
import org.hibernate.annotations.JavaType;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Shop extends AggregateRoot<Shop, ShopId> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JavaType(ShopIdJavaType.class)
    private ShopId id;

    @Version
    private Long version;

    @Column(name="NAME")
    private String name;

    @Column(name="MIN_ORDER_AMOUNT")
    private Money minOrderPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "OPERATION_HOURS", joinColumns = @JoinColumn(name = "SHOP_ID"))
    @MapKeyColumn(name="DAY_OF_WEEK")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DayOfWeek, TimePeriod> operatingHours;


    public boolean isOpen() {
        return isOpen(LocalDateTime.now());
    }

    public Shop(String name, boolean open, Money minOrderPrice, Map<DayOfWeek, TimePeriod> operatingHours) {
        this(null, name, open, minOrderPrice, operatingHours);
    }

    @Builder
    public Shop(ShopId id, String name, boolean open, Money minOrderPrice, Map<DayOfWeek, TimePeriod> operatingHours) {
        this.id = id;
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.operatingHours = operatingHours;
    }

    Shop() {
    }


    public boolean isOpen(LocalDateTime time) {
        if (!operatingHours.containsKey(time.getDayOfWeek())) {
            return false;
        }

        return operatingHours.get(time.getDayOfWeek()).contains(time.toLocalTime());
    }

    public void putOffOneHourOn(DayOfWeek dayOfWeek) {
        if (!operatingHours.containsKey(dayOfWeek)) {
            return;
        }

        TimePeriod period = operatingHours.get(dayOfWeek);
        operatingHours.put(dayOfWeek, period.putOffHours(1));
    }
}
