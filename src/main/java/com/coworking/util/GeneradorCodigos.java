package com.coworking.util;

import java.util.Random;

public final class GeneradorCodigos {

    private GeneradorCodigos(){
    }
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final Random random = new Random();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();


        // Agregar caracteres alfanuméricos aleatorios
        for (int i = 0; i < length; i++) {
            boolean useAlphabet = random.nextBoolean();
            if (useAlphabet) {
                int index = random.nextInt(ALPHABET.length());
                sb.append(ALPHABET.charAt(index));
            } else {
                int index = random.nextInt(NUMBERS.length());
                sb.append(NUMBERS.charAt(index));
            }
        }

        return sb.toString();
    }

}
