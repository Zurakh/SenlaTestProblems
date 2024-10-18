package org.example.javazero_1;

public class ViewImp implements View {

    private static final String[] hangAsci = {
            "+---+\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            " +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " /    |\n" +
                    "      |\n" +
                    "=========",

            "  +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " / \\  |\n" +
                    "      |\n" +
                    "========="
    };

    @Override
    public String getOpenedLetters(String word, boolean[] lettersStatus) {
        var sb = new StringBuilder();
        for (int i = 0; i < word.length(); ++i) {
            if (lettersStatus[i]) {
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

    public String getFailGuessResult(int stage) {
        return "No such letters found\n" + getHangedMan(stage);
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
    public String getWordView(String word) {
        return word;
    }

    @Override
    public String getRemainingLivesView(int lives) {
        return lives + " live(s) remaining";
    }

    public String getHangedMan(int stage) {
        return hangAsci[stage];
    }
}
