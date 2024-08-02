package com.mechanics.school.utils;

import java.security.SecureRandom;

public class RandomCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomCode() {
        StringBuilder id = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            id.append(CHARACTERS.charAt(index));
        }
        return id.toString();
    }
    public static String generateSixDigitCode() {
        int code = RANDOM.nextInt(900000) + 100000; // Generates a number between 100000 and 999999
        return String.valueOf(code);
    }
}