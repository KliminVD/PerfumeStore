package com.example.courseproject.Entities;

import java.util.Random;

public class IdGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int ID_LENGTH = 20;
    private static final Random random = new Random();

    public static String generateId() {
        long timestamp = System.currentTimeMillis();
        StringBuilder idBuilder = new StringBuilder(Long.toString(timestamp, 36));

        for (int i = 0; i < ID_LENGTH - idBuilder.length(); i++) {
            int index = random.nextInt(CHARACTERS.length());
            idBuilder.append(CHARACTERS.charAt(index));
        }

        return idBuilder.toString();
    }
}
