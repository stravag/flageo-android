package ch.ranil.android.flageo.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class QuizBuilderTest {

    @Before
    public void setup() {
        FlagQuizBuilder.newInstance();
    }

    @Test
    public void testFlagQuizBuilding() throws FlagQuizBuilder.NothingToQuizException {
        Quiz<Flag> quiz = FlagQuizBuilder.getInstance().buildQuiz(4);

        Set<Flag> flagSet = new HashSet<>();
        flagSet.addAll(Arrays.asList(quiz.getOptions()));

        Assert.assertEquals(4, flagSet.size());
    }

    @Test(expected = FlagQuizBuilder.NothingToQuizException.class)
    public void testAllAnswered1() throws FlagQuizBuilder.NothingToQuizException {
        for (int i = 0; i <= Flag.values().length; i++) {
            FlagQuizBuilder.getInstance().buildQuiz(4);
        }
    }

    @Test(expected = FlagQuizBuilder.NothingToQuizException.class)
    public void testAllAnswered2() throws FlagQuizBuilder.NothingToQuizException {
        for (int i = 0; i <= Flag.values().length; i++) {
            FlagQuizBuilder.getInstance().nextUnasked();
        }
    }
}
