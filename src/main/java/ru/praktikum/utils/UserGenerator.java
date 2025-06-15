package ru.praktikum.utils;

import java.util.Random;

/**
 * Класс для генерации случайных пользовательских данных.
 */
public class UserGenerator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final Random rnd = new Random();

    /**
     * Генерирует случайное имя пользователя с префиксом "client_".
     * @return Сгенерированное имя.
     */
    public static String createUsername() {
        return "client_" + generateRandomText(6);
    }

    /**
     * Генерирует случайный email с префиксом "user_" и доменом "@test.com".
     * @return Сгенерированный email.
     */
    public static String createEmail() {
        return "user_" + generateRandomText(6) + "@test.com";
    }

    /**
     * Генерирует случайный пароль заданной длины.
     * @param length Длина пароля.
     * @return Сгенерированный пароль.
     */
    public static String createPassword(int length) {
        return generateRandomText(length);
    }

    /**
     * Генерирует случайную строку из букв заданной длины.
     * @param length Длина строки.
     * @return Сгенерированная строка.
     */
    private static String generateRandomText(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
        }
        return result.toString();
    }
}