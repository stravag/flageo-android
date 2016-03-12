package ch.ranil.android.flageo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.model.CameraPosition;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.fragment.Flag2MapQuizFragment;
import ch.ranil.android.flageo.fragment.Flag2NameQuizFragment;
import ch.ranil.android.flageo.fragment.Name2FlagQuizFragment;
import ch.ranil.android.flageo.fragment.QuizListener;
import ch.ranil.android.flageo.fragment.QuizResultFragment;

public class QuizActivity extends AppCompatActivity implements QuizListener {

    private static final String TAG = "QuizActivity";

    public static final String PARAM_MODE = "mode";

    public static final String MODE_NAME_TO_FLAG = "modeName2Flag";
    public static final String MODE_FLAG_TO_NAME = "modeFlag2Name";
    public static final String MODE_FLAG_TO_MAP = "modeFlag2Map";

    // Milliseconds, MUSTN'T EXCEED INTEGER-MAXVALUE!
    // Potentially dangerous but simple solution to use timer data for progressbar progress
    private static final long TIMER = 60000;
    private static final long TIMER_INTERVAL = 100; // ms

    private static final int NUMBER_OF_CHOICES = 4;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private int score = 0;
    private String mode;
    private CountDownTimer timer;
    private long remainingMillis = TIMER;

    private CameraPosition cameraPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        mode = getIntent().getStringExtra(PARAM_MODE);

        progressBar.setMax((int) TIMER);
        progressBar.setProgress(0);

        loadQuiz();
        startCountdown(TIMER, 0);
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
        Fragment quizFragment;
        switch (mode) {
            case MODE_FLAG_TO_NAME:
                quizFragment = Flag2NameQuizFragment.newInstance(NUMBER_OF_CHOICES);
                break;
            case MODE_FLAG_TO_MAP:
                quizFragment = Flag2MapQuizFragment.newInstance(cameraPosition);
                break;
            case MODE_NAME_TO_FLAG:
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

    @Override
    public void cameraPosition(CameraPosition cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    @Override
    public void timeBoost(long addedMillis) {
        timer.cancel();
        progressBar.setMax((int) (progressBar.getMax() + addedMillis));
        startCountdown(remainingMillis + addedMillis, progressBar.getProgress());
        Toast.makeText(this, String.format("Boosted time by %dms", addedMillis), Toast.LENGTH_SHORT).show();
    }
}
