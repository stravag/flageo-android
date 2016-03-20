package ch.ranil.android.flageo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.fragment.Flag2MapQuizFragment;
import ch.ranil.android.flageo.fragment.Flag2NameQuizFragment;
import ch.ranil.android.flageo.fragment.Name2FlagQuizFragment;
import ch.ranil.android.flageo.fragment.QuizListener;
import ch.ranil.android.flageo.fragment.QuizResultFragment;
import ch.ranil.android.flageo.model.FlagQuizBuilder;
import ch.ranil.android.flageo.model.Mode;
import ch.ranil.android.flageo.storage.FlageoStorage;

public class QuizActivity extends AppCompatActivity implements QuizListener {

    private static final String TAG = "QuizActivity";

    public static final String PARAM_MODE = "mode";

    private static final long TIMER = 60000;
    private static final long TIMER_INTERVAL = 100; // ms

    private static final int NUMBER_OF_CHOICES = 4;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private int score = 0;
    private Mode mode;
    private CountDownTimer timer;
    private long remainingMillis = TIMER;

    private Map<Mode, Fragment> fragments = new HashMap<>(Mode.values().length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        mode = (Mode) getIntent().getSerializableExtra(PARAM_MODE);

        // I know, it's potentially dangerous to use the timer scale for the progressbar (long > int)
        // but I guess it's highly unlikely someone boosts the timer above Integer.MAX_VALUE and it makes
        // it a lot easier to manage the progressbar progress.
        progressBar.setMax((int) TIMER);
        progressBar.setProgress(0);

        FlagQuizBuilder.newInstance();
        loadQuiz();
        startCountdown(TIMER, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void startCountdown(final long millisInFuture, final int progressOffset) {

        Log.d(TAG, String.format("starting countdown for %dms", millisInFuture));

        timer = new CountDownTimer(millisInFuture, TIMER_INTERVAL) {

            @Override
            public void onTick(long millisUntilFinished) {
                remainingMillis = millisUntilFinished;
                int progress = (int) (millisInFuture - millisUntilFinished) + progressOffset;
//                Log.d(TAG, String.format("%d / %d, progress=%d", millisUntilFinished, millisInFuture, progressBar.getProgress()));
                progressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(progressBar.getMax());
                showResult();
            }
        };
        timer.start();
    }

    /**
     * Loads and shows the next quiz fragment.
     */
    private void loadQuiz() {
        Fragment quizFragment = getFragment(mode);
        setTitle(mode.getTitle());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.fragment_container, quizFragment, mode.toString())
                .commit();
    }

    /**
     * Get the fragment, use cached variant if available.
     *
     * @param mode quiz mode
     * @return quiz fragment
     */
    private Fragment getFragment(Mode mode) {
        Fragment fragment = fragments.get(mode);
        if (fragment == null) {
            switch (mode) {
                case FLAG2NAME:
                    fragment = Flag2NameQuizFragment.newInstance(NUMBER_OF_CHOICES);
                    break;
                case FLAG2MAP:
                    fragment = Flag2MapQuizFragment.newInstance();
                    fragments.put(mode, fragment);
                    break;
                case NAME2FLAG:
                    fragment = Name2FlagQuizFragment.newInstance(NUMBER_OF_CHOICES);
            }
        } else if (fragment instanceof Flag2MapQuizFragment) {
            ((Flag2MapQuizFragment) fragment).loadQuiz();
        }

        return fragment;
    }

    /**
     * Shows the quiz result screen once the timer finished.
     */
    private void showResult() {

        // save record
        int record = FlageoStorage.setRecord(score, mode, this);
        QuizResultFragment resultFragment = QuizResultFragment.newInstance(score, record);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, resultFragment);

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

    @Override
    public void timeBoost(long addedMillis) {
        timer.cancel();
        progressBar.setMax((int) (progressBar.getMax() + addedMillis));
        startCountdown(remainingMillis + addedMillis, progressBar.getProgress());
    }

    @Override
    public void answeredAllQuestions() {
        timer.cancel();
        timer.onFinish();
    }
}
