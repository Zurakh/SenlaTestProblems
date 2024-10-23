package org.example.javazero_1;

import java.util.*;


public class HangmanImpl implements Hangman{
    private String word;
    private Status status;
    private int lives;
    private int initLives;
    private boolean[] openedLetters;
    private int numOpened;

    private int[] prevPos;
    private Map<Character, Integer> lastPos;
    private int failCount;


    public HangmanImpl(String word, int initLives) {
        assert initLives > 0 && word != null && word.length() > 0;
        this.lives = initLives;
        setWord(word);
    }
    public HangmanImpl(int initLives) {
        assert initLives > 0;
        this.initLives = initLives;
        status = Status.UNPREPARED;
    }
    @Override
    public String getWord() {
        return word;
    }

    @Override
    public boolean isLetterGuessed(int pos) {
        assert pos >= 0 && pos < word.length();
        return openedLetters[pos];
    }

    @Override
    public void start() {
        start(0);
    }

    @Override
    public void start(int numOpened) {
        assert numOpened >= 0 && numOpened < word.length() && status == Status.PREPARED;

        //Arrays.fill(openedLetters, false);

        Random random = new Random();
        this.status = Status.PLAYING;
        failCount = 0;

        List<Integer> randomNumbers = random.ints(0, word.length())
                .distinct()
                .limit(numOpened)
                .boxed()
                .toList();

        for (int ind : randomNumbers) {
            openLetter(ind);
        }

        this.lives = initLives;
        System.out.println("Lives: " + this.lives);
    }

    @Override
    public void openLetter(int pos) {
        assert pos >= 0 && pos < word.length();
        openedLetters[pos] = true;
        numOpened++;
        if (numOpened == word.length()) {
            status = Status.WON;
        }
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public int numberGuessed() {
        return numOpened;
    }


    @Override
    public int guessLetter(char letter) {
        assert status == Status.PLAYING;

        Integer index = lastPos.getOrDefault(letter, -1);
        int counter = 0;

        if (index == -1) {
            setLives(lives - 1);
            failCount++;
        } else {
            lastPos.remove(letter);
        }

        while (index != -1) {
            if (!isLetterGuessed(index)) {
                openLetter(index);
                counter++;
            }
            index = prevPos[index];
        }

        return counter;
    }


    // Validate words with throwing exceptions
    @Override
    public void setWord(String word) {
        assert word.length() > 0;
        word = word.split(" ")[0];
        lastPos = new HashMap<Character, Integer>();
        prevPos = new int[word.length()];
        openedLetters = new boolean[word.length()];

        this.word = word;
        this.status = Status.PREPARED;

        for (int i = 0; i < word.length(); ++i) {
            prevPos[i] = lastPos.getOrDefault(word.charAt(i), -1);
            lastPos.put(word.charAt(i), i);
        }

        this.numOpened = 0;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        assert lives >= 0;
        this.lives = lives;
        if (lives == 0) {
            status = Status.LOST;
        } else if (status == Status.LOST) {
            status = Status.PREPARED;
        }
    }

    @Override
    public boolean[] getLettersStatus() {
        return Arrays.copyOf(openedLetters, openedLetters.length);
    }

    @Override
    public int getFails() {
        return failCount;
    }
}
