package ch.ranil.android.flageo.storage;

import android.content.Context;
import android.content.SharedPreferences;

import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.model.Mode;

/**
 * Simple class to help handle key-value store.
 */
public class FlageoStorage {

    private static final String KEY_RECORD_ = "KEY_RECORD_";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static int getRecord(Mode mode, Context context) {
        return getPrefs(context).getInt(KEY_RECORD_ + mode, 0);
    }

    public static int setRecord(int record, Mode mode, Context context) {
        int currentRecord = getRecord(mode, context);
        if (currentRecord < record) {
            getPrefs(context).edit().putInt(KEY_RECORD_ + mode, record).commit();
            return record;
        }
        return currentRecord;
    }
}
