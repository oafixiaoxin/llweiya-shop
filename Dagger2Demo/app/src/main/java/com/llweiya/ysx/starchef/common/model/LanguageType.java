package com.llweiya.ysx.starchef.common.model;

public enum LanguageType {
    CHINESE("ch"),
    ENGLISH("en"),
    THAILAND("th");

    private String language;

    LanguageType(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language == null ? "" : language;
    }
}
