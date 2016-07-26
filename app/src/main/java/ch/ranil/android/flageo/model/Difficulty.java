package ch.ranil.android.flageo.model;

import ch.ranil.android.flageo.R;

public enum Difficulty implements Quizable {

    EASY(R.string.easy, 0),
    MEDIUM(R.string.medium, 1),
    HARD(R.string.hard, 2),
    VERY_HARD(R.string.very_hard, 3);

    private int translation;
    private int similars;

    Difficulty(int translation, int similars) {
        this.translation = translation;
        this.similars = similars;
    }

    public int getTranslation() {
        return translation;
    }

    public int getSimilars() {
        return similars;
    }
}
