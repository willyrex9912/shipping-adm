package com.modela.shipping.adm.util;

public enum CategoryEnum {

    STATUS(500L),
        STAT_ACTIVE(501L),
        STAT_INACTIVE(502L),
        STAT_DELETED(503L),
    PACKAGE_ROUTE_STATUS(525L),
        PRS_REGISTERED(526L),
        PRS_PENDING(527L),
        PRS_IN_TRANSIT(528L),
        PRS_COMPLETED(529L);

    CategoryEnum(Long internalId) {
        this.internalId = internalId;
    }

    public final Long internalId;
}
