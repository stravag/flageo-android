package ch.ranil.android.flageo.model;

import ch.ranil.android.flageo.R;

/**
 * @author ranil wijeyratne
 */
public enum Flag implements Quizable {

    AFGHANISTAN(R.drawable.af, R.string.afghanistan, 652230),
    ALBANIA(R.drawable.al, R.string.albania, 28748),
    ALGERIA(R.drawable.dz, R.string.algeria, 2381741),
    ANDORRA(R.drawable.ad, R.string.andorra, 468),
    ANGOLA(R.drawable.ao, R.string.angola),
    ANTIGUA(R.drawable.ag, R.string.antigua),
    ARGENTINA(R.drawable.ar, R.string.argentina),
    ARMENIA(R.drawable.am, R.string.armenia),
    AUSTRALIA(R.drawable.au, R.string.australia),
    AUSTRIA(R.drawable.at, R.string.austria),
    AZERBAIJAN(R.drawable.az, R.string.azerbaijan),

    UGANDA(R.drawable.ug, R.string.uganda),
    UKRAINE(R.drawable.ua, R.string.ukraine),
    UAE(R.drawable.ae, R.string.uae),
    UK(R.drawable.gb, R.string.uk),
    URUGUAY(R.drawable.uy, R.string.uruguay),
    UZBEKISTAN(R.drawable.uz, R.string.uzbekistan),
    ;

    private final static long REFERENCE_AREA = 17098242;

    private int drawable;
    private int translation;
    private long area = REFERENCE_AREA;

    Flag(int drawable, int translation) {
        this.drawable = drawable;
        this.translation = translation;
    }

    Flag(int drawable, int translation, long area) {
        this.drawable = drawable;
        this.translation = translation;
        this.area = area;
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
     * Small countries get a time boost when in map quiz mode.
     * Calculation based on area in square kilometers: TODO find a better metric
     *
     * @return boost time in ms
     */
    public long getTimeBoost() {
        return Math.max(REFERENCE_AREA / area, 5000);
    }
}
