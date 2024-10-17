package org.example.javazero_1;

public class ViewImp implements ViewManager {
    private final Hangman hangman;
    public ViewImp(Hangman hangman) {
        this.hangman = hangman;
    }

    @Override
    public String getOpenedLetters() {
        var sb = new StringBuilder();
        String word = hangman.getWord();
        for (int i = 0; i < word.length(); ++i) {
            if (hangman.isLetterGuessed(i)) {
                sb.append(word.charAt(i));
            } else {
                sb.append("_");
            }
        }
        return sb.toString();
    }

    @Override
    public String getFailGuessResult() {
        return "No such letters found";
    }

    @Override
    public String getSuccessGuessResult(int amount) {
        return "found " + amount + " such letter(s)";
    }

    @Override
    public String getSuccessGuessResult() {
        return "found letters";
    }

    @Override
    public String getWonResult() {
        return "You won";
    }

    @Override
    public String getLostResult() {
        return "You lost";
    }

    @Override
    public String getWord() {
        return hangman.getWord();
    }

    @Override
    public String getRemainingLives() {
        return hangman.getLives() + " live(s) remaining";
    }
}
