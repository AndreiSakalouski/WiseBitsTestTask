package com.wisebits.testapi.enums;

public enum ResponseMessages {
    SUCCESSFULLY_CREATED("User Successully created"),
    USERNAME_TAKEN("This username is taken. Try another."),
    EMAIL_ALREADY_EXISTS("Email already exists"),
    EMPTY_PASSWORD("A password for the user"),
    EMPTY_USERNAME("A username is required"),
    EMPTY_EMAIL("An Email is required");

    private final String messageText;

    public String getMessageText() {
        return this.messageText;
    }

    ResponseMessages(String messageText) {
        this.messageText = messageText;
    }
}
