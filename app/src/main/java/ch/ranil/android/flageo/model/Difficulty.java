package ch.ranil.android.flageo.model;

import ch.ranil.android.flageo.R;

public enum Difficulty implements Quizable {

    EASY(R.string.easy, 0, -1000, 1),
    MEDIUM(R.string.medium, 1, -1333, 3),
    HARD(R.string.hard, 2, -1666, 5),
    VERY_HARD(R.string.very_hard, 3, -2000, -1);

    private int translation;
    private int similars;
    private int penalty;
    private int maxwrong;

    Difficulty(int translation, int similars, int penalty, int maxwrong) {
        this.translation = translation;
        this.similars = similars;
        this.penalty = penalty;
        this.maxwrong = maxwrong;
    }

    public int getTranslation() {
        return translation;
    }

    public int getSimilars() {
        return similars;
    }

    public int getPenalty() {
        return penalty;
    }

    public int getMaxwrong() {
        return maxwrong;
    }

    @Override
    public String toString() {
        if (this == EASY) {
            return ""; // ugly but ensures backwards compatibility for now
        } else {
            return super.toString();
        }
    }
}
