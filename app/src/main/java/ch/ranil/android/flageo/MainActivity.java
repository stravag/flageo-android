package ch.ranil.android.flageo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.model.Flag;
import ch.ranil.android.flageo.model.Quiz;
import ch.ranil.android.flageo.model.QuizBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    private static final long TIMER = 10000; // 10s
    private static final long TIMER_INTERVAL = 10; // 10ms
    private static final int NUMBER_OF_CHOICES = 4;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.txv_flagAsked)
    TextView flagAsked;

    private ImageButton[] flagButtons = new ImageButton[NUMBER_OF_CHOICES];
    private Quiz<Flag> quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initializeQuizView();

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

    /**
     * Initialize the flag buttons by selecting random flags and assigning them to the buttons.
     */
    private void initializeQuizView() {


        flagButtons[0] = (ImageButton) findViewById(R.id.btn_flag1);
        flagButtons[1] = (ImageButton) findViewById(R.id.btn_flag2);
        flagButtons[2] = (ImageButton) findViewById(R.id.btn_flag3);
        flagButtons[3] = (ImageButton) findViewById(R.id.btn_flag4);

        quiz = QuizBuilder.buildFlagQuiz(Flag.class, NUMBER_OF_CHOICES);
        Log.d(TAG, quiz.toString());

        flagAsked.setText(quiz.getAnswer().getTranslation());

        for (int i = 0; i < NUMBER_OF_CHOICES; i++) {
            Flag f = quiz.getOption(i);
            flagButtons[i].setImageResource(f.getDrawable());
            flagButtons[i].setOnClickListener(flagButtonClickListener);
        }
    }

    private View.OnClickListener flagButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_flag1:
                    processAnswer(0);
                    return;
                case R.id.btn_flag2:
                    processAnswer(1);
                    return;
                case R.id.btn_flag3:
                    processAnswer(2);
                    return;
                case R.id.btn_flag4:
                    processAnswer(3);
                    return;
                default:
                    Toast.makeText(getBaseContext(), "Unexpected button pressed: " + v.getId(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private boolean processAnswer(int answer) {
        if (quiz.isCorrect(answer)) {
            Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getBaseContext(), "Wrong you fool!!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
