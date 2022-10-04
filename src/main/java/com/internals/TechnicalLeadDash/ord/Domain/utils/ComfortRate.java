package com.internals.TechnicalLeadDash.ord.Domain.utils;

public enum ComfortRate {
    NOTATALL(Integer.valueOf(0)),
    SlightlyAware(Integer.valueOf(1)),
    SomewhatAware(Integer.valueOf(2)),
    ModeratelyComfortable(Integer.valueOf(3)),
    HighlyIncomfortable(Integer.valueOf(4)),
    ;

    ComfortRate(Integer valueOf) {
    }
}
