package ch.ranil.android.flageo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FlagQuizBuilder {

    private static FlagQuizBuilder instance;
    private List<Flag> unasked;

    public static FlagQuizBuilder getInstance() {
        if (instance == null) {
            throw new IllegalStateException("FlagQuizBuilder not initialized, make sure you create an instance first.");
        }
        return instance;
    }

    public static FlagQuizBuilder newInstance() {
        instance = new FlagQuizBuilder();
        return instance;
    }

    private FlagQuizBuilder() {
        unasked = new ArrayList<>();
        unasked.addAll(getQuizables());
    }

    private List<Flag> getQuizables() {
        List<Flag> quizables = new ArrayList<>();
        Collections.addAll(quizables, Flag.values());
        return quizables;
    }

    /**
     * Gets a random unasked flag.
     *
     * @return flag
     * @throws NothingToQuizException
     */
    public Flag nextUnasked() throws NothingToQuizException {
        if (unasked.isEmpty()) {
            throw new NothingToQuizException();
        }

        Random r = ThreadLocalRandom.current();
        int index = r.nextInt(unasked.size());
        index = index == unasked.size() ? index - 1 : index;

        return unasked.remove(index);
    }

    /**
     * Creates a random quiz of unasked flags.
     *
     * @param size number of options
     * @return quiz
     * @throws NothingToQuizException
     */
    public Quiz<Flag> buildQuiz(int size) throws NothingToQuizException {

        Flag answer = nextUnasked();
        Random r = ThreadLocalRandom.current();

        List<Flag> quizables = getQuizables();

        Flag[] options = new Flag[size];
        quizables.remove(answer);
        options[0] = answer;
        for (int i = 1; i < size; i++) {
            options[i] = quizables.remove(r.nextInt(quizables.size() - 1));
        }

        shuffleArray(options);

        for (int i = 0; i < options.length; i++) {
            if (options[i] == answer) {
                return new Quiz<>(options, i);
            }
        }

        throw new RuntimeException(
                String.format("Something odd just happened... couldn't find answer (%s) for the quiz (%s) I just created",
                        answer.toString(), Arrays.toString(options)));
    }

    // Implementing Fisher–Yates shuffle
    private static <T> void shuffleArray(T[] ar) {

        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            T a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public class NothingToQuizException extends Exception {
    }
}
