package org.example.javazero_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        WordProvider wordProvider = new WordProviderHardCodedEnglishSeven();
        String word = wordProvider.getWord();
        Hangman game = new HangmanImpl(word, 6);
        ViewImp view = new ViewImp();
        HangmanHolder holder = new HangmanHolder(wordProvider, view, game);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        holder.start(2);

        while (game.getStatus() == Status.PLAYING) {
            String input = reader.readLine();
            if (input.length() == 0)
                continue;

            holder.guessLetter(input.charAt(0));
        }

    }
}
