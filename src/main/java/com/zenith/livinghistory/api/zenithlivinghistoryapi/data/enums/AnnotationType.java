package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.enums;

public enum AnnotationType {
    TEXT("TextualBody"),
    IMAGE("Image");

    private String text;

    AnnotationType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
