package ch.ranil.android.flageo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ranil.android.flageo.fragment.Flag2NameQuizFragment;
import ch.ranil.android.flageo.fragment.Name2FlagQuizFragment;
import ch.ranil.android.flageo.fragment.QuizAnswerListener;
import ch.ranil.android.flageo.fragment.QuizResultFragment;

public class QuizActivity extends AppCompatActivity implements QuizAnswerListener {

    public static final String PARAM_MODE = "mode";

    public static final String MODE_NAME_TO_FLAG = "modeName2Flag";
    public static final String MODE_FLAG_TO_NAME = "modeFlag2Name";

    private static final long TIMER = 15000; // ms
    private static final long TIMER_INTERVAL = 100; // ms
    private static final int PROGRESS_SCALE = 1000;

    private static final int NUMBER_OF_CHOICES = 4;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private int score = 0;
    private String mode;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        mode = getIntent().getStringExtra(PARAM_MODE);

        loadQuiz();
        initializeCountdown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    private void initializeCountdown() {
        progressBar.setMax(PROGRESS_SCALE);
        progressBar.setProgress(0);
        timer = new CountDownTimer(TIMER, TIMER_INTERVAL) {

            @Override
            public void onTick(long millisUntilFinished) {
                long millisPassed = TIMER - millisUntilFinished;
                int progress = (int) (millisPassed * PROGRESS_SCALE / TIMER);
                progressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(PROGRESS_SCALE);
                showResult();
            }
        };
        timer.start();
    }

    /**
     * Loads and shows the next quiz fragment.
     */
    private void loadQuiz() {
        Fragment quizFragment;
        switch (mode) {
            case MODE_FLAG_TO_NAME:
                quizFragment = Flag2NameQuizFragment.newInstance(NUMBER_OF_CHOICES);
                break;
            default:
                quizFragment = Name2FlagQuizFragment.newInstance(NUMBER_OF_CHOICES);
        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, quizFragment, mode);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    /**
     * Shows the quiz result screen once the timer finished.
     */
    private void showResult() {
        QuizResultFragment resultFragment = QuizResultFragment.newInstance(score);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, resultFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    /**
     * Quiz fragment callback method.
     *
     * @param correct true if the quiz fragment was answered correctly.
     */
    @Override
    public void quizAnswered(boolean correct) {
        if (correct) {
            score++;
        }

        loadQuiz();
    }

}
