package org.example.javazero_1;

public class HangmanHolder {
    private final WordProvider wordProvider;
    private final ViewManager viewManager;
    private final Hangman hangman;

    public HangmanHolder(WordProvider wordProvider, ViewManager viewManager, Hangman hangman) {
        this.wordProvider = wordProvider;
        this.viewManager = viewManager;
        this.hangman = hangman;
    }


    public WordProvider getWordProvider() {
        return wordProvider;
    }



    public ViewManager getViewManager() {
        return viewManager;
    }



    public Hangman getHangman() {
        return hangman;
    }

    public void start(String word) {
        hangman.setWord(word);
    }

    public void start() {
        String word = wordProvider.getWord();
        getHangman().setWord(word);
    }

    public void start(int openedLetters) {
        getHangman().start(openedLetters);
        System.out.println(viewManager.getOpenedLetters());
    }


    public int getLives() {
        return getHangman().getLives();
    }

    public void setLives(int lives) {
        getHangman().setLives(lives);
    }

    public Status getStatus() {
        return getHangman().getStatus();
    }

    public void guessLetter(char c) {
        assert hangman.getStatus() == Status.PLAYING;
        int res = getHangman().guessLetter(c);
        if (res <= 0) {
            System.out.println(viewManager.getFailGuessResult());
            if (hangman.getStatus() == Status.LOST) {
                System.out.println(viewManager.getWord());
                System.out.println(viewManager.getLostResult());
                return;
            }
        } else {
            System.out.println(viewManager.getSuccessGuessResult(res));
            if (hangman.getStatus() == Status.WON) {
                System.out.println(viewManager.getWord());
                System.out.println(viewManager.getWonResult());
                return;
            }
        }
        System.out.println(viewManager.getOpenedLetters());
        System.out.println(viewManager.getRemainingLives());
    }

    public void setWord(String word) {
        getHangman().setWord(word);
    }

    public int numberGuessed() {
        return getHangman().numberGuessed();
    }

    public String getWord() {
        return getHangman().getWord();
    }

    public boolean isLetterGuessed(int pos) {
        return getHangman().isLetterGuessed(pos);
    }
}
