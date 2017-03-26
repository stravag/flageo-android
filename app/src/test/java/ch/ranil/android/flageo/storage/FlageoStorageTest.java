package ch.ranil.android.flageo.storage;

import org.junit.Test;

import ch.ranil.android.flageo.model.Difficulty;
import ch.ranil.android.flageo.model.Mode;

import static ch.ranil.android.flageo.storage.FlageoStorage.RECORD_KEY_PREFIX;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;

public class FlageoStorageTest {

    @Test
    public void recordKey() {

        assertThat(FlageoStorage.buildRecordKey(Mode.FLAG2MAP, Difficulty.EASY), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("FLAG2MAP")));
        assertThat(FlageoStorage.buildRecordKey(Mode.FLAG2MAP, Difficulty.MEDIUM), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("FLAG2MAPHARD")));
        assertThat(FlageoStorage.buildRecordKey(Mode.FLAG2MAP, Difficulty.HARD), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("FLAG2MAPVERY_HARD")));

        assertThat(FlageoStorage.buildRecordKey(Mode.FLAG2NAME, Difficulty.EASY), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("FLAG2NAME")));
        assertThat(FlageoStorage.buildRecordKey(Mode.FLAG2NAME, Difficulty.MEDIUM), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("FLAG2NAMEHARD")));
        assertThat(FlageoStorage.buildRecordKey(Mode.FLAG2NAME, Difficulty.HARD), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("FLAG2NAMEVERY_HARD")));

        assertThat(FlageoStorage.buildRecordKey(Mode.NAME2FLAG, Difficulty.EASY), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("NAME2FLAG")));
        assertThat(FlageoStorage.buildRecordKey(Mode.NAME2FLAG, Difficulty.MEDIUM), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("NAME2FLAGHARD")));
        assertThat(FlageoStorage.buildRecordKey(Mode.NAME2FLAG, Difficulty.HARD), allOf(
                startsWith(RECORD_KEY_PREFIX),
                endsWith("NAME2FLAGVERY_HARD")));

    }
}
