package com.modela.shipping.adm.util;

public enum CategoryEnum {

    STATUS(500L),
        STAT_ACTIVE(501L),
        STAT_INACTIVE(502L),
        STAT_DELETED(503L);

    CategoryEnum(Long internalId) {
        this.internalId = internalId;
    }

    public final Long internalId;
}
