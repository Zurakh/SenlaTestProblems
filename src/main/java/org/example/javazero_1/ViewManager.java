package org.example.javazero_1;

public interface ViewManager {
    public String getOpenedLetters();
    public String getFailGuessResult();
    public String getSuccessGuessResult(int amount);
    public String getSuccessGuessResult();
    public String getWonResult();
    public String getLostResult();
    public String getWord();
    public String getRemainingLives();
}
