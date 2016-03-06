package ch.ranil.android.flageo.model;

import ch.ranil.android.flageo.R;

/**
 * @author ranil wijeyratne
 */
public enum Flag implements Quizable {

    AFGHANISTAN(R.drawable.af, R.string.afghanistan, 3),
    ALBANIA(R.drawable.al, R.string.albania, 3),
    ALGERIA(R.drawable.dz, R.string.algeria, 2),
    ANDORRA(R.drawable.ad, R.string.andorra, 3),
    ANGOLA(R.drawable.ao, R.string.angola, 2),
    ANTIGUA(R.drawable.ag, R.string.antigua, 3),
    ARGENTINA(R.drawable.ar, R.string.argentina, 1),
    ARMENIA(R.drawable.am, R.string.armenia, 3),
    AUSTRALIA(R.drawable.au, R.string.australia, 3),
    AUSTRIA(R.drawable.at, R.string.austria, 1),
    AZERBAIJAN(R.drawable.az, R.string.azerbaijan, 3),

    UGANDA(R.drawable.ug, R.string.uganda, 3),
    UKRAINE(R.drawable.ua, R.string.ukraine, 3),
    UAE(R.drawable.ae, R.string.uae, 3),
    UK(R.drawable.gb, R.string.uk, 3),
    URUGUAY(R.drawable.uy, R.string.uruguay, 2),
    UZBEKISTAN(R.drawable.uz, R.string.uzbekistan, 3),
    ;

    private int drawable;
    private int translation;
    private int difficulty;

    Flag(int drawable, int translation, int difficulty) {
        this.drawable = drawable;
        this.translation = translation;
        this.difficulty = difficulty;
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

}
