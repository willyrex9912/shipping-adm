package com.modela.shipping.adm.dto;

import java.util.Collection;

public class ShippingPage<T extends Collection<?>, Long> {

    public final T items;
    public final Long count;

    private ShippingPage(T items, Long count) {
        this.items = items;
        this.count = count;
    }

    public static <T extends Collection<?>, Long> ShippingPage<T, Long> of(T items, Long count) {
        return new ShippingPage<>(items, count);
    }
}
