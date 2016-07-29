package ch.ranil.android.flageo.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

        Assert.assertEquals(4, flagSet.size());
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
            Assert.assertTrue("Not enough (" + similarFlags.size() + ") similar flag found for: " + flag,
                              3 <= similarFlags.size());
        }
    }
}
