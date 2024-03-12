package com.zazergel.configuration;

public abstract class Config {
    public static final String PAGE_DEFAULT_FROM = "0";
    public static final String PAGE_DEFAULT_SIZE = "10";

    public static final int MAX_LENGTH_NAME_CATEGORY = 20;
    public static final int MIN_LENGTH_NAME_CATEGORY = 2;

    public static final int MAX_LENGTH_NAME_PRODUCT = 30;
    public static final int MIN_LENGTH_NAME_PRODUCT = 2;

    public static final int MIN_LENGTH_DESCRIPTION_PRODUCT = 10;
    public static final int MAX_LENGTH_DESCRIPTION_PRODUCT = 100;

    public static final int MIN_LENGTH_COMMENT = 2;
    public static final int MAX_LENGTH_COMMENT = 500;

    private Config() {
    }
}