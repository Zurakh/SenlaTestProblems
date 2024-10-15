package org.example.javazero_1;

import java.util.HashMap;
import java.util.Map;

enum GameStatus implements Status {
        LOST, WON, PLAYING
}
public class HangmanImpl implements Hangman{
    private String word;
    private Status status;
    private int lifes;
    private boolean[] foundLetters;
    private int numberGuessed;

    private int[] prevPos;
    private Map<Character, Integer> lastPos;
    @Override
    public String getWord() {
        return word;
    }
    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public int numberGuessed() {
        return numberGuessed;
    }

    public boolean[] getFoundLetters() {
        return foundLetters;
    }

    // Maybe I should add exception for calling method while it's in wrong state
    @Override
    public boolean guessLetter(char c) {
        if (status != GameStatus.PLAYING) {
            return false;
        }
        Integer ind = lastPos.get(c);
        if (ind == null) {
            lifes--;
            if (lifes <= 0) {
                status = GameStatus.LOST;
            }
            return false;
        }
        lastPos.remove(c);
        while (ind != -1) {
            numberGuessed++;
            foundLetters[ind] = true;
            ind = prevPos[ind];
        }

        if (numberGuessed == word.length()) {
            status = GameStatus.WON;
        }

        return true;
    }


    // Validate words with throwing exceptions
    @Override
    public void setWord(String word) {
        lastPos = new HashMap<Character, Integer>();
        foundLetters = new boolean[word.length()];
        this.word = word;
        this.status = GameStatus.PLAYING;
        this.numberGuessed = 0;
        for (int i = 0; i < word.length(); ++i) {
            prevPos[i] = lastPos.getOrDefault(word.charAt(i), -1);
            lastPos.put(word.charAt(i), i);
        }
    }
}
