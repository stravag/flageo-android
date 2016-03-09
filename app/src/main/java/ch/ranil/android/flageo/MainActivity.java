package ch.ranil.android.flageo;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ranil.android.flageo.fragment.Name2FlagQuizFragment;
import ch.ranil.android.flageo.fragment.QuizResultFragment;

public class MainActivity extends AppCompatActivity implements Name2FlagQuizFragment.QuizAnswerListener {

    private static final String TAG = "MainActivity";

    private static final long TIMER = 15000; // 10s
    private static final long TIMER_INTERVAL = 5; // 5ms

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

    private void loadQuiz() {
        Name2FlagQuizFragment quizFragment = Name2FlagQuizFragment.newInstance(4);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, quizFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    private void showResult() {
        QuizResultFragment resultFragment = QuizResultFragment.newInstance(score);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, resultFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    private void initializeCountdown() {
        progressBar.setMax(100);
        progressBar.setProgress(0);
        CountDownTimer timer = new CountDownTimer(10000, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) (100 - (millisUntilFinished / 100));
                progressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(500);
                Toast.makeText(getBaseContext(), "Finished", Toast.LENGTH_SHORT).show();
                showResult();
            }
        };
        timer.start();
    }

    @Override
    public void quizAnswered(boolean correct) {
        if (correct) {
            Toast.makeText(this, "Correct 8)", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Wrong dummy :#", Toast.LENGTH_SHORT).show();
        }

        loadQuiz();
    }

    @OnClick(R.id.restart)
    public void restartQuiz() {
        recreate();
    }
}
