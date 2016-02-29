package ch.ranil.android.flageo.model;

import ch.ranil.android.flageo.R;

/**
 * @author ranil wijeyratne
 */
public enum Flag {

    ANDORRA(R.drawable.ad, R.string.flag_andorra, 3, "Andorra"),
    UAE(R.drawable.ae, R.string.flag_uae, 3, "United Arab Emirates"),
    AFGHANISTAN(R.drawable.af, R.string.flag_afghanistan, 3, "Afghanistan"),
    ANTIGUA(R.drawable.ag, R.string.flag_antigua, 3, "Antigua and Barbuda"),
    ALBANIA(R.drawable.al, R.string.flag_antigua, 3, "Albania"),
    ;

    private int drawable;
    private int translation;
    private int difficulty;
    private String mapsName;

    Flag(int drawable, int translation, int difficulty, String mapsName) {
        this.drawable = drawable;
        this.translation = translation;
        this.difficulty = difficulty;
        this.mapsName = mapsName;
    }

    /**
     * The flag drawable.
     *
     * @return drawable resource number
     */
    public int getDrawable() {
        return drawable;
    }

    /**
     * Translated flag name
     *
     * @return string resource id
     */
    public int getTranslation() {
        return translation;
    }

    /**
     * Flag difficulty
     *
     * @return difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Name of country on google maps.
     *
     * @return name string
     */
    public String getMapsName() {
        return mapsName;
    }
}
