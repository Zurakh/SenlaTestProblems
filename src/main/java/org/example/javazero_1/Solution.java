package org.example.javazero_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        WordProvider wordProvider = new WordProviderHardCodedEnglishSeven();
        Hangman game = new HangmanImpl(6);
        ViewImp view = new ViewImp();
        HangmanHolder holder = new HangmanHolder(wordProvider, view, game);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = "r";
        while ("r".equals(input)) {
            holder.start(2);
            while (game.getStatus() == Status.PLAYING) {
                input = reader.readLine();
                if (input.length() == 0)
                    continue;

                holder.guessLetter(input.charAt(0));
            }
            System.out.println("Press r to restart");
            System.out.println("Press any other key to quit");
            input = reader.readLine();
        }

    }
}
