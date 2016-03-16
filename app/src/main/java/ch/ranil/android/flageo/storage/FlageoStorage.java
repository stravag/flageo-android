package ch.ranil.android.flageo.storage;

import android.content.Context;
import android.content.SharedPreferences;

import ch.ranil.android.flageo.R;

/**
 * Simple class to help handle key-value store.
 */
public class FlageoStorage {

    private static final String KEY_RECORD_FLAG2NAME = "KEY_RECORD_FLAG2NAME";
    private static final String KEY_RECORD_NAME2FLAG = "KEY_RECORD_NAME2FLAG";
    private static final String KEY_RECORD_FLAG2MAP = "KEY_RECORD_FLAG2MAP";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static int getFlag2NameRecord(Context context) {
        return getPrefs(context).getInt(KEY_RECORD_FLAG2NAME, 0);
    }

    public static int setFlag2NameRecord(int record, Context context) {
        int currentRecord = getFlag2NameRecord(context);
        if (currentRecord < record) {
            getPrefs(context).edit().putInt(KEY_RECORD_FLAG2NAME, record).commit();
            return record;
        }
        return currentRecord;
    }

    public static int getFlag2MapRecord(Context context) {
        return getPrefs(context).getInt(KEY_RECORD_FLAG2MAP, 0);
    }

    public static int setFlag2MapRecord(int record, Context context) {
        int currentRecord = getFlag2MapRecord(context);
        if (currentRecord < record) {
            getPrefs(context).edit().putInt(KEY_RECORD_FLAG2MAP, record).commit();
            return record;
        }
        return currentRecord;
    }

    public static int getName2FlagRecord(Context context) {
        return getPrefs(context).getInt(KEY_RECORD_NAME2FLAG, 0);
    }

    public static int setName2FlagRecord(int record, Context context) {
        int currentRecord = getName2FlagRecord(context);
        if (currentRecord < record) {
            getPrefs(context).edit().putInt(KEY_RECORD_NAME2FLAG, record).commit();
            return record;
        }
        return currentRecord;
    }
}
