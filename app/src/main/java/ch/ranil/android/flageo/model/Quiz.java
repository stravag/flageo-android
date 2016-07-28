package ch.ranil.android.flageo.model;

import java.util.Arrays;

public class Quiz<T extends Quizable> {

    private final T[] options;
    private final int answerIndex;

    public Quiz(T[] options, T answer) {

        this.options = options;

        for (int i = 0; i < options.length; i++) {
            if (options[i] == answer) {
                this.answerIndex = i;
                return;
            }
        }

        throw new IllegalArgumentException(
                String.format("Something odd just happened... couldn't find answer (%s) for the quiz (%s)",
                answer.toString(), Arrays.toString(options)));
    }

    public T[] getOptions() {
        return options;
    }

    public T getOption(int index) {
        return options[index];
    }

    public T getAnswer() {
        return options[answerIndex];
    }

    public boolean isCorrect(int index) {
        return index == answerIndex;
    }

    @Override
    public String toString() {
        return "Quiz{" + "options=" + Arrays.toString(options) + ", answerIndex=" + answerIndex + '}';
    }
}
