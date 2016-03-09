package ch.ranil.android.flageo;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.fragment.Name2FlagQuizFragment;

public class MainActivity extends AppCompatActivity implements Name2FlagQuizFragment.QuizAnswerListener {

    private static final long TIMER = 10000; // 10s
    private static final long TIMER_INTERVAL = 10; // 10ms

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

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

    private void initializeCountdown() {
        progressBar.setMax((int) (TIMER / TIMER_INTERVAL));
        CountDownTimer timer = new CountDownTimer(TIMER, TIMER_INTERVAL) {

            int i = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(i++);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getBaseContext(), "Finished", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
    }

    @Override
    public void quizAnswered(boolean correct) {
        if (correct)
            Toast.makeText(this, "Correct 8)", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Wrong dummy :#", Toast.LENGTH_SHORT).show();

        loadQuiz();
    }
}
