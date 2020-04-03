package com.erank.applerssfeed.models;

public enum ExplicitMode {
    YES("explicit"),
    NO("non-explicit");

    public String val;

    ExplicitMode(String val) {
        this.val = val;
    }
}
