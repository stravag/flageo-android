package ch.ranil.android.flageo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.cache.BitmapCache;
import ch.ranil.android.flageo.model.Flag;
import ch.ranil.android.flageo.model.Quiz;
import ch.ranil.android.flageo.model.QuizBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizAnswerListener} interface
 * to handle interaction events.
 */
public class Flag2NameQuizFragment extends Fragment {

    private static final String PARAM_NUMBER_OF_CHOICES = "numberOfChoices";

    @Bind(R.id.txt_flagAsked)
    ImageView flagAsked;

    private int numberOfChoices = 4;
    private Quiz<Flag> quiz;
    private QuizAnswerListener answerListener;

    /**
     * Fragment construction helper.
     *
     * @param numberOfChoices number of flag choices
     * @return fragment instance
     */
    public static Flag2NameQuizFragment newInstance(int numberOfChoices) {
        Flag2NameQuizFragment fragment = new Flag2NameQuizFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_NUMBER_OF_CHOICES, numberOfChoices);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            numberOfChoices = getArguments().getInt(PARAM_NUMBER_OF_CHOICES);
        }

        quiz = QuizBuilder.buildFlagQuiz(Flag.class, numberOfChoices);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_flag2name_quiz, container, false);
        ButterKnife.bind(this, fragmentLayout);

        Button[] flagButtons = new Button[numberOfChoices];

        // TODO add dynamically to layout
        flagButtons[0] = (Button) fragmentLayout.findViewById(R.id.btn_flag1);
        flagButtons[1] = (Button) fragmentLayout.findViewById(R.id.btn_flag2);
        flagButtons[2] = (Button) fragmentLayout.findViewById(R.id.btn_flag3);
        flagButtons[3] = (Button) fragmentLayout.findViewById(R.id.btn_flag4);

        BitmapCache.getInstance().loadBitmap(quiz.getAnswer().getDrawable(), flagAsked);

        for (int i = 0; i < flagButtons.length; i++) {
            Flag f = quiz.getOption(i);
            flagButtons[i].setText(f.getTranslation());
            flagButtons[i].setOnClickListener(flagButtonClickListener);
        }

        return fragmentLayout;
    }

    /**
     * Track the click events of the answer buttons.
     */
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
            }
        }
    };

    /**
     * Check if the selected answer is correct and send info back to activity.
     *
     * @param answer selected answer
     */
    private void processAnswer(int answer) {
        answerListener.quizAnswered(quiz.isCorrect(answer));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof QuizAnswerListener) {
            answerListener = (QuizAnswerListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement QuizAnswerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        answerListener = null;
    }


}
