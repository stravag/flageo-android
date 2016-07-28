package ch.ranil.android.flageo.storage;

import android.content.Context;
import android.content.SharedPreferences;

import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.model.Difficulty;
import ch.ranil.android.flageo.model.Mode;

/**
 * Simple class to help handle key-value store.
 */
public class FlageoStorage {

    private static final String KEY_RECORD_ = "KEY_RECORD_";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static int getRecord(Mode mode, Difficulty difficulty, Context context) {
        return getPrefs(context).getInt(KEY_RECORD_ + mode + difficulty.toString(), 0);
    }

    public static int setRecord(int record, Mode mode, Difficulty difficulty, Context context) {
        int currentRecord = getRecord(mode, difficulty, context);
        if (currentRecord < record) {
            getPrefs(context).edit().putInt(KEY_RECORD_ + mode + difficulty.toString(), record).commit();
            return record;
        }
        return currentRecord;
    }

    public static boolean isDifficultyActive(Difficulty difficulty, Context context) {
        for (Mode mode : Mode.values()) {
            if (getRecord(mode, difficulty, context) == 0) {
                return false;
            }
        }
        return true;
    }
}
