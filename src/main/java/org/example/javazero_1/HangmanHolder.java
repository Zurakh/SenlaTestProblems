package org.example.javazero_1;

public class HangmanHolder {
    private final WordProvider wordProvider;
    private final View view;
    private final Hangman hangman;

    public HangmanHolder(WordProvider wordProvider, View view, Hangman hangman) {
        assert(wordProvider != null && view != null && hangman != null);
        this.wordProvider = wordProvider;
        this.view = view;
        this.hangman = hangman;
    }


    public WordProvider getWordProvider() {
        return wordProvider;
    }

    public View getViewManager() {
        return view;
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
        System.out.println(view.getOpenedLetters(hangman.getWord(), hangman.getLettersStatus()));
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
            if (hangman.getStatus() == Status.LOST) {
                System.out.println(view.getWordView(hangman.getWord()));
                System.out.println(view.getLostResult());
                System.out.println(view.getFailGuessResult(hangman.getFails()));
                return;
            }
            System.out.println(view.getFailGuessResult());
        } else {
            System.out.println(view.getSuccessGuessResult(res));
            if (hangman.getStatus() == Status.WON) {
                System.out.println(view.getWordView(hangman.getWord()));
                System.out.println(view.getWonResult());
                return;
            }
        }
        System.out.println(view.getOpenedLetters(hangman.getWord(), hangman.getLettersStatus()));
        System.out.println(view.getRemainingLivesView(hangman.getLives()));
        System.out.println(view.getFailGuessResult(hangman.getFails()));
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