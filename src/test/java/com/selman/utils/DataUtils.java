package com.selman.utils;

import com.github.javafaker.Faker;

public class DataUtils {
    private static final Faker faker = new Faker();

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String getRandomPassword() {
        return faker.internet().password(8, 16, true); // 8-16 karakter, büyük harf dahil
    }
}