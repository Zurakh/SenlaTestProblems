package org.example.javazero_1;

public class ViewImp {
    private final Hangman hangman;
    public ViewImp(Hangman hangman) {
        this.hangman = hangman;
    }

    public String printOpenedLetters() {
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
}
