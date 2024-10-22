package org.example.javazero_1;

public interface View {
    public String getOpenedLetters(String word, boolean[] lettersStatus);
    public String getFailGuessResult();
    public String getFailGuessResult(int numFails);
    public String getSuccessGuessResult(int amount);
    public String getSuccessGuessResult();
    public String getWonResult();
    public String getLostResult();
    public String getWordView(String word);
    public String getRemainingLivesView(int lives);
}
