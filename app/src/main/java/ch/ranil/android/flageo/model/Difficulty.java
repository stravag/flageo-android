package ch.ranil.android.flageo.model;

import ch.ranil.android.flageo.R;

public enum Difficulty implements Quizable {

    EASY(R.string.easy, -1000, 1, ""),
    // previously known as HARD
    MEDIUM(R.string.medium, -1666, 5, "HARD"),
    // previously known as VERY_HARD
    HARD(R.string.hard, -2000, Integer.MAX_VALUE, "VERY_HARD");

    private final int translation;
    private final int penalty;
    private final int maxwrong;

    // don't change my recordName
    // i'm needed to preserve old records
    private final String recordName;

    Difficulty(int translation, int penalty, int maxwrong, String recordName) {
        this.translation = translation;
        this.penalty = penalty;
        this.maxwrong = maxwrong;
        this.recordName = recordName;
    }

    public int getTranslation() {
        return translation;
    }

    public int getPenalty() {
        return penalty;
    }

    public int getMaxwrong() {
        return maxwrong;
    }

    public String getRecordName() {
        return recordName;
    }
}
