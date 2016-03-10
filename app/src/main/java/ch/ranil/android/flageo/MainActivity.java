package ch.ranil.android.flageo;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ranil.android.flageo.fragment.Name2FlagQuizFragment;
import ch.ranil.android.flageo.fragment.QuizResultFragment;

public class MainActivity extends AppCompatActivity implements Name2FlagQuizFragment.QuizAnswerListener {

    private static final long TIMER = 15000; // ms
    private static final long TIMER_INTERVAL = 100; // ms
    private static final int PROGRESS_SCALE = 1000;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        loadQuiz();
        initializeCountdown();
    }

    private void initializeCountdown() {
        progressBar.setMax(PROGRESS_SCALE);
        progressBar.setProgress(0);
        CountDownTimer timer = new CountDownTimer(TIMER, TIMER_INTERVAL) {

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
        Name2FlagQuizFragment quizFragment = Name2FlagQuizFragment.newInstance(4);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, quizFragment);
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

    /**
     * For development
     */
    @OnClick(R.id.restart)
    public void restartQuiz() {
        recreate();
    }
}
