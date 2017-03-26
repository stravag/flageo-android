package ch.ranil.android.flageo.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.model.Difficulty;
import ch.ranil.android.flageo.model.Mode;

/**
 * Simple class to help handle key-value store.
 */
public class FlageoStorage {

    private static final String TAG = "FlageoStorage";
    static final String RECORD_KEY_PREFIX = "KEY_RECORD_";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static int getRecord(Mode mode, Difficulty difficulty, Context context) {
        return getPrefs(context)
                .getInt(buildRecordKey(mode, difficulty), 0);
    }

    public static int setRecord(int record, Mode mode, Difficulty difficulty, Context context) {
        int currentRecord = getRecord(mode, difficulty, context);
        if (currentRecord < record) {
            getPrefs(context)
                    .edit()
                    .putInt(buildRecordKey(mode, difficulty), record)
                    .apply();
            return record;
        }
        return currentRecord;
    }

    static String buildRecordKey(Mode mode, Difficulty difficulty) {
        return RECORD_KEY_PREFIX + mode + difficulty.getRecordName();
    }

    public static boolean isDifficultyActive(Difficulty difficulty, Context context) {

        switch (difficulty) {
            case EASY:
                return true;
            case MEDIUM:
                difficulty = Difficulty.EASY;
                break;
            case HARD:
                difficulty = Difficulty.MEDIUM;
                break;
        }

        for (Mode mode : Mode.values()) {
            int record = getRecord(mode, difficulty, context);
            Log.d(TAG, mode.name() + "/" + difficulty.name() + "/" + record);
            if (record == 0) {
                return false;
            }
        }
        return true;
    }
}
