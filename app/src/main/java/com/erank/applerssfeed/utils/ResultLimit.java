package com.erank.applerssfeed.utils;

enum ResultLimit {
    MIN("10"),
    SOME("25"),
    HALF("50"),
    ALL("100");

    public final String val;

    ResultLimit(String val) {
        this.val = val;
    }
}
