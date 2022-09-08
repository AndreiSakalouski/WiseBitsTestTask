package com.wisebits.testapi.util;

import lombok.experimental.UtilityClass;
import org.testng.annotations.DataProvider;

import static com.wisebits.testapi.util.TestDataFactory.*;
import static com.wisebits.testapi.enums.ResponseMessages.*;
@UtilityClass
public class TestDataProvider {
    @DataProvider(name = "emptyFieldsData")
    public Object[][] getTestData() {
        {
            return new Object[][] {
                    { getUserWithEmptyUsername(), EMPTY_USERNAME },
                    { getUserWithEmptyEmail(), EMPTY_EMAIL },
                    { getUserWithEmptyPassword(), EMPTY_PASSWORD }
            };
        }
    }
}
