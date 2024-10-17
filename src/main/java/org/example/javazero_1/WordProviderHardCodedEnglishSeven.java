package org.example.javazero_1;

import java.util.Random;

public class WordProviderHardCodedEnglishSeven implements WordProvider{
    private static final String[] words = {
            "feather",
            "goodbye",
            "revenge",
            "chassis",
            "ceramic",
            "apparel",
            "mammoth",
            "iceberg",
            "leading",
            "culvert",
            "arrival",
            "pennant",
            "pension",
            "webinar",
            "running",
            "fishing",
            "crewmen",
            "printer",
            "proctor",
            "flicker",
            "ecology",
            "cabinet",
            "latency",
            "sponsor",
            "husband",
            "deposit",
            "faculty",
            "termite",
            "council",
            "linseed",
            "concern",
            "diploma",
            "triumph",
            "creator",
            "heating",
            "percent",
            "shallot",
            "account",
            "shelter",
            "chalice",
            "seagull",
            "speaker",
            "anatomy",
            "saviour",
            "journal",
            "selling",
            "dueling",
            "inquiry",
            "ukulele",
            "decency"
    };
    private static Random random;

    public WordProviderHardCodedEnglishSeven() {
        random = new Random();
    }
    @Override
    public String getWord() {
        int ind = random.nextInt(words.length);
        return words[ind];
    }
}
