package ch.ranil.android.flageo.model;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class QuizBuilder {

    /**
     * Build a list (linkedlist) with all options of given quizzable enumerations.
     *
     * @return flag set
     */
    private static List<Flag> buildQuizSet() {

        final List<Flag> flags = new LinkedList<>();

        Collections.addAll(flags, Flag.values());

        return flags;
    }

    public static <T extends Quizable> Quiz<T> buildFlagQuiz(Class<T> clazz, int size) {

        T[] options = (T[]) Array.newInstance(clazz, size);

        List<Flag> flag = buildQuizSet();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            options[i] = (T) flag.remove(r.nextInt(flag.size() - 1));
        }

        int answer = r.nextInt(size - 1);

        return new Quiz<>(options, answer);
    }
}
