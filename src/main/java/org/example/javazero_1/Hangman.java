package org.example.javazero_1;

public interface Hangman {
    public Status getStatus();
    public boolean guessLetter(char c);
    public void setWord(String word);
    public int numberGuessed();
    public String getWord();
}
