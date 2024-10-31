package org.example.javazero_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PasswordGenerator {

    private static final char[] digits = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private static final char[] LowerCaseAlphabet = {
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
    };

    private static final char[] UpperCaseAlphabet = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };

    private static final char[] specialSymbols  = {
            '!', '@', '#', '$', '%', '^', '&', '&', '*', '(', ')', '-', '_', '+', '='
    };
    public static String generatePasswordUtilizingAllSets(char[][] charSets, int length) {
        if (charSets.length > length) {
            throw new IllegalArgumentException("All sets can't be utilized for password. Amount of sets is " +
                    charSets.length + " but length is " + length);
        }
        if (length == 0) {
            throw new IllegalArgumentException("Password must be non-empty");
        }

        var ans = new ArrayList<String>(length);

        int usedCharsAmount = 0;
        int nonUtilizedCategories = charSets.length;

        Random random = new Random();

        for (int i = 1; i <= charSets.length; i++) {
            int charAmount = i != charSets.length?
                    random.nextInt(1, length - nonUtilizedCategories - i + 1) :
                    length -  usedCharsAmount;
            for (int j = 0; j < charAmount; j++) {
                int randomCharNumber = random.nextInt(0, charSets[i-1].length);
                char randomChar = charSets[i - 1][randomCharNumber];
                ans.add(String.valueOf(randomChar));
            }
            nonUtilizedCategories--;
            usedCharsAmount += charAmount;
        }

        Collections.shuffle(ans);

        return String.join("", ans);
    }

    public static String generatePassword(int length) {
        return generatePasswordUtilizingAllSets(new char[][]{
                digits, LowerCaseAlphabet, UpperCaseAlphabet, specialSymbols
        }, length);
    }

    public static String generatePassword() {
        Random random = new Random();
        return generatePassword(random.nextInt(8, 12+1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите длину пароля");
        int len = 0;
        while (len < 8 || len > 12) {
            System.out.println("Длина пароля должна быть от 8 до 12 влючительно");
            len = Integer.parseInt(reader.readLine());
        }
        System.out.println(generatePassword(len));
    }
}
