package ch.ranil.android.flageo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.model.Flag;

public class MainActivity extends AppCompatActivity {

    private static final long TIMER = 10000; // 10s
    private static final long TIMER_INTERVAL = 10; // 10ms
    private static final int NUMBER_OF_CHOICES = 4;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private ImageButton[] flagButtons = new ImageButton[NUMBER_OF_CHOICES];
    private Flag[] flags = new Flag[NUMBER_OF_CHOICES];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initializeFlagButtons();

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
    private void initializeFlagButtons() {
        flagButtons[0] = (ImageButton) findViewById(R.id.btn_flag1);
        flagButtons[1] = (ImageButton) findViewById(R.id.btn_flag2);
        flagButtons[2] = (ImageButton) findViewById(R.id.btn_flag3);
        flagButtons[3] = (ImageButton) findViewById(R.id.btn_flag4);

        for (int i = 0; i < NUMBER_OF_CHOICES; i++) {
            Flag f = randomFlag();
            flags[i] = f;
            flagButtons[i].setImageResource(f.getDrawable());
            flagButtons[i].setOnClickListener(flagButtonClickListener);
        }
    }

    private Flag randomFlag() {
        Random r = new Random();
        return Flag.values()[r.nextInt(Flag.values().length)];
    }

    private View.OnClickListener flagButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_flag1:
                    Toast.makeText(getBaseContext(), "Clicked flag1: " + flags[0], Toast.LENGTH_SHORT).show();
                    return;
                case R.id.btn_flag2:
                    Toast.makeText(getBaseContext(), "Clicked flag2: " + flags[1], Toast.LENGTH_SHORT).show();
                    return;
                case R.id.btn_flag3:
                    Toast.makeText(getBaseContext(), "Clicked flag3: " + flags[2], Toast.LENGTH_SHORT).show();
                    return;
                case R.id.btn_flag4:
                    Toast.makeText(getBaseContext(), "Clicked flag4: " + flags[3], Toast.LENGTH_SHORT).show();
                    return;
                default:
                    Toast.makeText(getBaseContext(), "Unexpected button pressed: " + v.getId(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
