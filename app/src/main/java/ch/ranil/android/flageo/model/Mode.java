package ch.ranil.android.flageo.model;

import ch.ranil.android.flageo.R;

public enum Mode {
    FLAG2NAME(R.string.mode_flag2name),
    NAME2FLAG(R.string.mode_name2flag),
    FLAG2MAP(R.string.mode_flag2map);

    private int title;

    Mode(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }
}
