package com.modela.shipping.adm.util;

public enum CategoryEnum {

    STATUS(500L),
        STAT_ACTIVE(501L),
        STAT_INACTIVE(502L),
        STAT_DELETED(503L),
    PACKAGE_ROUTE_STATUS(520L),
        PRS_REGISTERED(521L),
        PRS_PENDING(522L),
        PRS_IN_TRANSIT(523L),
        PRS_COMPLETED(524L);

    CategoryEnum(Long internalId) {
        this.internalId = internalId;
    }

    public final Long internalId;
}
