package ch.ranil.android.flageo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_quizFlag2Name)
    public void startFlag2NameQuiz() {
        startQuiz(QuizActivity.MODE_FLAG_TO_NAME);
    }

    @OnClick(R.id.btn_quizFlag2Map)
    public void startFlag2MapQuiz() {
        startQuiz(QuizActivity.MODE_FLAG_TO_MAP);
    }

    @OnClick(R.id.btn_quizName2Flag)
    public void startName2FlagQuiz() {
        startQuiz(QuizActivity.MODE_NAME_TO_FLAG);
    }

    private void startQuiz(String mode) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.PARAM_MODE, mode);
        startActivity(intent);
    }
}
