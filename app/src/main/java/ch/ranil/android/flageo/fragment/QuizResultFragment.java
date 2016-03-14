package ch.ranil.android.flageo.fragment;


import android.app.Fragment;
import android.os.Bundle;
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

    @Bind(R.id.txt_score)
    TextView scoreView;

    @Bind(R.id.txt_comment)
    TextView commentView;

    private int score;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QuizResultFragment.
     */
    public static QuizResultFragment newInstance(int score) {
        QuizResultFragment fragment = new QuizResultFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_SCORE, score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt(PARAM_SCORE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_quiz_result, container, false);
        ButterKnife.bind(this, fragmentLayout);

        scoreView.setText(getActivity().getString(R.string.score, score));

        if (score > 60) {
            commentView.setText(R.string.comment_4);
        } else if (score > 40) {
            commentView.setText(R.string.comment_3);
        } else if (score > 20) {
            commentView.setText(R.string.comment_2);
        } else if (score > 5) {
            commentView.setText(R.string.comment_1);
        } else if (score == 0) {
            commentView.setText(R.string.comment_0);
        }

        return fragmentLayout;
    }

}
