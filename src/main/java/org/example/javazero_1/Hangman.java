package org.example.javazero_1;

public interface Hangman {
    public Status getStatus();
    public int guessLetter(char c);
    public void setWord(String word);
    public int numberGuessed();
    public String getWord();
    public boolean isLetterGuessed(int pos);
    public void start();
    public void start(int openedLetters);
    public void openLetter(int pos);

    public int getLives();
    public void setLives(int lives);

    public boolean[] getLettersStatus();
    public int getFails();
}
