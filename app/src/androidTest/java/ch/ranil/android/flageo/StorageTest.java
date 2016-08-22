package ch.ranil.android.flageo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.ranil.android.flageo.model.Difficulty;
import ch.ranil.android.flageo.model.Mode;
import ch.ranil.android.flageo.storage.FlageoStorage;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class StorageTest {

    private static final String TAG = "StorageTest";

    private Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        clearSharedPrefs();
    }

    @Before
    public void tearDown() {
        clearSharedPrefs();
    }

    @Test
    public void testDifficultyUnlock() {

        Difficulty playable = Difficulty.EASY;
        for (Difficulty difficulty : Difficulty.values()) {
            Log.d(TAG, "Testing unlocking of: " + difficulty.name());
            if (difficulty == Difficulty.EASY) {
                assertTrue(difficulty.name(), FlageoStorage.isDifficultyActive(difficulty, context));
                continue;
            }

            assertFalse(difficulty.name(), FlageoStorage.isDifficultyActive(difficulty, context));

            FlageoStorage.setRecord(1, Mode.FLAG2NAME, playable, context);
            assertFalse(difficulty.name(), FlageoStorage.isDifficultyActive(difficulty, context));

            FlageoStorage.setRecord(1, Mode.NAME2FLAG, playable, context);
            assertFalse(difficulty.name(), FlageoStorage.isDifficultyActive(difficulty, context));

            FlageoStorage.setRecord(1, Mode.FLAG2MAP, playable, context);
            assertTrue(difficulty.name(), FlageoStorage.isDifficultyActive(difficulty, context));

            playable = difficulty;
        }
    }

    @Test
    public void increaseRecord() {
        assertEquals(0, FlageoStorage.setRecord(-1, Mode.FLAG2MAP, Difficulty.EASY, context));
        assertEquals(1, FlageoStorage.setRecord(1, Mode.FLAG2MAP, Difficulty.EASY, context));
        assertEquals(5, FlageoStorage.setRecord(5, Mode.FLAG2MAP, Difficulty.EASY, context));
    }

    /**
     * Clears everything in the SharedPreferences
     */
    @SuppressLint("CommitPrefEdits")
    private void clearSharedPrefs() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
