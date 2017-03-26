package ch.ranil.android.flageo.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FlagQuizBuilderTest {

    @Before
    public void setup() {
        FlagQuizBuilder.newInstance();
    }

    @Test
    public void flagQuizBuilding() throws FlagQuizBuilder.NothingToQuizException {
        Quiz<Flag> quiz = FlagQuizBuilder.getInstance().buildQuiz(4, Difficulty.EASY);

        Set<Flag> flagSet = new HashSet<>();
        flagSet.addAll(Arrays.asList(quiz.getOptions()));

        assertEquals(4, flagSet.size());
    }

    @Test(expected = FlagQuizBuilder.NothingToQuizException.class)
    public void allAnswered1() throws FlagQuizBuilder.NothingToQuizException {
        for (int i = 0; i <= Flag.values().length; i++) {
            FlagQuizBuilder.getInstance().buildQuiz(4, Difficulty.EASY);
        }
    }

    @Test(expected = FlagQuizBuilder.NothingToQuizException.class)
    public void allAnswered2() throws FlagQuizBuilder.NothingToQuizException {
        for (int i = 0; i <= Flag.values().length; i++) {
            FlagQuizBuilder.getInstance().nextUnasked();
        }
    }

    @Test
    public void ensureEachFlagHasOneSimilar() {

        for (Flag flag : Flag.values()) {
            Set<Flag> similarFlags = FlagQuizBuilder.getSimilarFlags(flag);
            assertTrue("Not enough (" + similarFlags.size() + ") similar flag found for: " + flag,
                              3 <= similarFlags.size());
        }
    }

    @Test
    public void similarCount() {

        assertThat(FlagQuizBuilder.numberOfSimilar(Difficulty.EASY), is(0));
        for (int i = 0; i < 100; i++) {
            assertThat("attempt #" + i,
                    FlagQuizBuilder.numberOfSimilar(Difficulty.MEDIUM),
                    anyOf(is(1), is(2), is(3)));
        }
        assertThat(FlagQuizBuilder.numberOfSimilar(Difficulty.HARD), is(3));
    }
}
