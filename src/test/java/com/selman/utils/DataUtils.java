package com.selman.utils;

import com.github.javafaker.Faker;

public class DataUtils {
    private static final Faker faker = new Faker();

    /**
     * Generates a random email address.
     * Useful for registration tests to avoid duplicate user errors.
     */
    public static String getRandomEmail() { return faker.internet().emailAddress(); }
    public static String getRandomFirstName() { return faker.name().firstName(); }
    public static String getRandomLastName() { return faker.name().lastName(); }

    public static String getRandomPassword() {
        // Generate a password with 8 to 16 chars, including uppercase
        return faker.internet().password(8, 16, true);
    }

    public static String getRandomAddress() { return faker.address().streetAddress(); }
    public static String getRandomState() { return faker.address().state(); }
    public static String getRandomCity() { return faker.address().city(); }
    public static String getRandomZipCode() { return faker.address().zipCode(); }
    public static String getRandomMobileNumber() { return faker.phoneNumber().cellPhone(); }
}