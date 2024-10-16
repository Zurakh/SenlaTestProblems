package org.example.javazero_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        WordProvider wordProvider = new WordProviderHardCoded();
        String word = wordProvider.getWord();
        Hangman game = new HangmanImpl(word, 3);
        ViewImp view = new ViewImp(game);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        game.start(1);
        System.out.println(view.printOpenedLetters());

        while (game.getStatus() == Status.PLAYING) {
            char input = reader.readLine().charAt(0);
            boolean res = game.guessLetter(input);
            if (!res) {
                System.out.println("No letter");
            }
            System.out.println(view.printOpenedLetters());
        }
        if (game.getStatus() == Status.LOST) {
            System.out.println("You lost");
        } else {
            System.out.println("You won");
        }
    }
}
