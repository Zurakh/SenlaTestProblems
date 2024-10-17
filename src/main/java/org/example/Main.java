package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var sb = new StringBuilder();
        var sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String word = sc.next();
            sb.append('"').append(word).append('"').append(',').append('\n');
        }
        System.out.println(sb);
    }
}