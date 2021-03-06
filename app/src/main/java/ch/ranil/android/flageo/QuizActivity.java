package ch.ranil.android.flageo;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.fragment.Flag2MapQuizFragment;
import ch.ranil.android.flageo.fragment.Flag2NameQuizFragment;
import ch.ranil.android.flageo.fragment.Name2FlagQuizFragment;
import ch.ranil.android.flageo.fragment.QuizListener;
import ch.ranil.android.flageo.fragment.QuizResultFragment;
import ch.ranil.android.flageo.model.Difficulty;
import ch.ranil.android.flageo.model.FlagQuizBuilder;
import ch.ranil.android.flageo.model.Mode;
import ch.ranil.android.flageo.storage.FlageoStorage;
import ch.ranil.android.flageo.utils.UiUtils;

public class QuizActivity extends AppCompatActivity implements QuizListener {

    private static final String TAG = "QuizActivity";

    public static final String PARAM_MODE = "mode";
    public static final String PARAM_DIFFICULTY = "difficulty";

    private static final long TIMER = 60000;
    private static final long TIMER_INTERVAL = 100; // ms

    private static final int NUMBER_OF_CHOICES = 4;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.toolbar_score)
    TextView toolbarScore;

    private int score = 0;
    private Mode mode;
    private Difficulty difficulty;
    private CountDownTimer timer;
    private long remainingMillis = TIMER;

    private Map<Mode, Fragment> fragments = new HashMap<>(Mode.values().length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        }

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mode = (Mode) getIntent().getSerializableExtra(PARAM_MODE);
        difficulty = (Difficulty) getIntent().getSerializableExtra(PARAM_DIFFICULTY);

        // I know, it's potentially dangerous to use the timer scale for the progressbar (long > int)
        // but I guess it's highly unlikely someone boosts the timer above Integer.MAX_VALUE and it makes
        // it a lot easier to manage the progressbar progress.
        progressBar.setMax((int) TIMER);
        progressBar.setProgress(0);

        toolbarScore.setText(String.valueOf(score));

        FlagQuizBuilder.newInstance();
        loadQuiz();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    private void startCountdown(final long millisInFuture, final int progressOffset) {

        Log.d(TAG, String.format("starting countdown for %dms", millisInFuture));

        timer = new CountDownTimer(millisInFuture, TIMER_INTERVAL) {

            @Override
            public void onTick(long millisUntilFinished) {
                remainingMillis = millisUntilFinished;
                int progress = (int) (millisInFuture - millisUntilFinished) + progressOffset;
                progressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, String.format("timer finished (remainingMillis = %d, progress = %d)", remainingMillis, progressBar.getProgress()));
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
                    fragment = Flag2NameQuizFragment.newInstance(NUMBER_OF_CHOICES, difficulty);
                    break;
                case FLAG2MAP:
                    fragment = Flag2MapQuizFragment.newInstance(difficulty);
                    fragments.put(mode, fragment);
                    break;
                case NAME2FLAG:
                    fragment = Name2FlagQuizFragment.newInstance(NUMBER_OF_CHOICES, difficulty);
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

        toolbarScore.setVisibility(View.GONE);

        // save record
        int record = FlageoStorage.setRecord(score, mode, difficulty, this);
        QuizResultFragment resultFragment = QuizResultFragment.newInstance(score, record, mode);

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
            toolbarScore.setText(String.valueOf(score));
            UiUtils.flashView(toolbar, R.color.green_flash);
        }
        loadQuiz();
    }

    @Override
    public void timeBoost(long addedMillis) {
        Log.d(TAG, String.format("boosting countdown by %dms", addedMillis));
        if (timer != null) {
            timer.cancel();
        }
        progressBar.setMax((int) (progressBar.getMax() + addedMillis));
        startCountdown(remainingMillis + addedMillis, progressBar.getProgress());
    }

    @Override
    public void answeredAllQuestions() {
        if (timer != null) {
            timer.cancel();
            timer.onFinish();
        }
    }
}
