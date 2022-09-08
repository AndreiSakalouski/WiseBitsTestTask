package com.wisebits.testapi.util;

import com.wisebits.testapi.models.CreateUserDto;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class TestDataFactory {
    public CreateUserDto getUserWithUniqueData() {
        return new CreateUserDto()
                .username(getUniqueUsername())
                .email(getUniqueEmail())
                .password(getRandomPassword());
    }

    public CreateUserDto getUserWithTakenUsername(String takenUsername) {
        return new CreateUserDto()
                .username(takenUsername)
                .email(getUniqueEmail())
                .password(getRandomPassword());
    }

    public CreateUserDto getUserWithTakenEmail(String takenEmail) {
        return new CreateUserDto()
                .username(getUniqueUsername())
                .email(takenEmail)
                .password(getRandomPassword());
    }

    public CreateUserDto getUserWithEmptyUsername() {
        return new CreateUserDto()
                .username("")
                .email(getUniqueEmail())
                .password(getRandomPassword());
    }

    public CreateUserDto getUserWithEmptyEmail() {
        return new CreateUserDto()
                .username(getUniqueUsername())
                .email("")
                .password(getRandomPassword());
    }
    public CreateUserDto getUserWithEmptyPassword() {
        return new CreateUserDto()
                .username(getUniqueUsername())
                .email(getUniqueEmail())
                .password("");
    }
    private String getUniqueUsername() {
        return String.format("test_user_%s", UUID.randomUUID());
    }

    private String getUniqueEmail() {
        return String.format("%s@gmail.com", UUID.randomUUID());
    }

    private String getRandomPassword() {
        return UUID.randomUUID().toString();
    }
}
