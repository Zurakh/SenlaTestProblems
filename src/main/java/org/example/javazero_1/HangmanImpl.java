package org.example.javazero_1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class HangmanImpl implements Hangman{
    private String word;
    private Status status;
    private int lives;
    private boolean[] openedLetters;
    private int numOpened;

    private int[] prevPos;
    private Map<Character, Integer> lastPos;


    public HangmanImpl(String word, int lives) {
        this.lives = lives;
        setWord(word);
    }
    @Override
    public String getWord() {
        return word;
    }

    @Override
    public boolean isLetterGuessed(int pos) {
        return openedLetters[pos];
    }

    @Override
    public void start() {
        start(0);
    }

    @Override
    public void start(int numOpened) {
        Random random = new Random();
        this.numOpened = numOpened;
        this.status = Status.PLAYING;
        List<Integer> randomNumbers = random.ints(0, word.length())
                .distinct()
                .limit(numOpened)
                .boxed()
                .toList();

        for (int ind : randomNumbers) {
            openedLetters[ind] = true;
        }
    }

    @Override
    public void openLetter(int pos) {
        openedLetters[pos] = true;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public int numberGuessed() {
        return numOpened;
    }


    // Maybe I should add exception for calling method while it's in wrong state
    @Override
    public boolean guessLetter(char c) {
        if (status != Status.PLAYING) {
            return false;
        }
        Integer ind = lastPos.get(c);
        if (ind == null) {
            lives--;
            if (lives <= 0) {
                status = Status.LOST;
            }
            return false;
        }
        lastPos.remove(c);
        while (ind != -1) {
            numOpened++;
            openedLetters[ind] = true;
            ind = prevPos[ind];
        }

        if (numOpened == word.length()) {
            status = Status.WON;
        }

        return true;
    }


    // Validate words with throwing exceptions
    @Override
    public void setWord(String word) {
        lastPos = new HashMap<Character, Integer>();
        prevPos = new int[word.length()];
        openedLetters = new boolean[word.length()];
        this.word = word;
        this.status = Status.PREPARED;
        this.numOpened = 0;
        for (int i = 0; i < word.length(); ++i) {
            prevPos[i] = lastPos.getOrDefault(word.charAt(i), -1);
            lastPos.put(word.charAt(i), i);
        }
    }
}
