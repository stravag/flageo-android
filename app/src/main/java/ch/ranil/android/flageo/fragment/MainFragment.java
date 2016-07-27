package ch.ranil.android.flageo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ranil.android.flageo.QuizActivity;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.model.Difficulty;
import ch.ranil.android.flageo.model.Mode;
import ch.ranil.android.flageo.storage.FlageoStorage;

public class MainFragment extends Fragment {

    private static final String PARAM_DIFFICULTY = "difficulty";

    @Bind(R.id.txt_flag2Map_record)
    TextView recordFlag2Map;

    @Bind(R.id.txt_flag2Name_record)
    TextView recordFlag2Name;

    @Bind(R.id.txt_name2Flag_record)
    TextView recordName2Flag;

    private Difficulty difficulty;

    public static MainFragment newInstance(Difficulty difficulty) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_DIFFICULTY, difficulty);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            difficulty = (Difficulty) getArguments().getSerializable(PARAM_DIFFICULTY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, fragmentLayout);

        return fragmentLayout;
    }

    @Override
    public void onResume() {
        super.onResume();

        recordFlag2Map.setText(getString(R.string.record,
                FlageoStorage.getRecord(Mode.FLAG2MAP, difficulty, getActivity())));

        recordFlag2Name.setText(getString(R.string.record,
                FlageoStorage.getRecord(Mode.FLAG2NAME, difficulty, getActivity())));

        recordName2Flag.setText(getString(R.string.record,
                FlageoStorage.getRecord(Mode.NAME2FLAG, difficulty, getActivity())));
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
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra(QuizActivity.PARAM_MODE, mode);
        intent.putExtra(QuizActivity.PARAM_DIFFICULTY, difficulty);
        startActivity(intent);
    }
}
