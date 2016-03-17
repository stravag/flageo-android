package ch.ranil.android.flageo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizResultFragment extends Fragment {

    private static final String PARAM_SCORE = "score";
    private static final String PARAM_RECORD = "record";

    @Bind(R.id.txt_scoreIntro)
    TextView scoreIntro;

    @Bind(R.id.txt_score)
    TextView scoreView;

    @Bind(R.id.txt_comment)
    TextView commentView;

    private int score;
    private int record;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QuizResultFragment.
     */
    public static QuizResultFragment newInstance(int score, int record) {
        QuizResultFragment fragment = new QuizResultFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_SCORE, score);
        args.putInt(PARAM_RECORD, record);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt(PARAM_SCORE);
            record = getArguments().getInt(PARAM_RECORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_quiz_result, container, false);
        ButterKnife.bind(this, fragmentLayout);

        if (record == score && record > 0) {
            scoreIntro.setText(getActivity().getString(R.string.you_scored_record));
        }
        scoreView.setText(getActivity().getString(R.string.score, score));

        if (score >= 50) {
            commentView.setText(R.string.comment_4);
        } else if (score > 30) {
            commentView.setText(R.string.comment_3);
        } else if (score >= 15) {
            commentView.setText(R.string.comment_2);
        } else if (score > 5) {
            commentView.setText(R.string.comment_1);
        } else if (score == 0) {
            commentView.setText(R.string.comment_0);
        }

        return fragmentLayout;
    }

}
