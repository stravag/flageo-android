package ch.ranil.android.flageo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ranil.android.flageo.model.Mode;
import ch.ranil.android.flageo.storage.FlageoStorage;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.txt_flag2Map_record)
    TextView recordFlag2Map;

    @Bind(R.id.txt_flag2Name_record)
    TextView recordFlag2Name;

    @Bind(R.id.txt_name2Flag_record)
    TextView recordName2Flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        recordFlag2Map.setText(getString(R.string.record, FlageoStorage.getFlag2MapRecord(this)));
        recordFlag2Name.setText(getString(R.string.record, FlageoStorage.getFlag2NameRecord(this)));
        recordName2Flag.setText(getString(R.string.record, FlageoStorage.getName2FlagRecord(this)));
    }

    @OnClick(R.id.btn_quizFlag2Name)
    public void startFlag2NameQuiz() {
        startQuiz(Mode.FLAG2NAME);
    }

    @OnClick(R.id.btn_quizFlag2Map)
    public void startFlag2MapQuiz() {
        startQuiz(Mode.FLAG2MAP);
    }

    @OnClick(R.id.btn_quizName2Flag)
    public void startName2FlagQuiz() {
        startQuiz(Mode.NAME2FLAG);
    }

    private void startQuiz(Mode mode) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.PARAM_MODE, mode);
        startActivity(intent);
    }
}
