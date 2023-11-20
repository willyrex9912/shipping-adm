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
        PRS_COMPLETED(529L),
    PARAMETER_CATEGORY(540L),
        PC_PRIORITY_PARAMETER(541L),
            PP_TIME(542L),
            PP_COST(543L),
        PC_BEHAVIOR_PARAMETER(550L),
            BP_VEHICLE_RETURN(551L),
            BP_RECALCULATE_BY_NODE(552L),
        PC_CONFIGURATION_PARAMETER(560L),
            CP_MAXIMUM_WAITING_TIME(561L),
            CP_MINIMUM_CAPACITY(562L),
            CP_STORAGE_COST(563L),
    VEHICLE_CATEGORY(570L),
        VC_SMALL(571L),
        VC_MEDIUM(572L),
        VC_LARGE(573L),
        VC_OTHER(574L);

    CategoryEnum(Long internalId) {
        this.internalId = internalId;
    }

    public final Long internalId;
}
