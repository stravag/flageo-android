package ch.ranil.android.flageo.model;

import java.util.Arrays;

public class Quiz<T> {

    private final T[] options;
    private final int answerIndex;


    public Quiz(T[] options, int answer) {
        if (answer < 0 || answer >= options.length) {
            throw new IllegalArgumentException("Unexpected answer index");
        }

        this.options = options;
        this.answerIndex = answer;
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
