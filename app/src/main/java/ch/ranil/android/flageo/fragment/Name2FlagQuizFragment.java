package ch.ranil.android.flageo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.cache.BitmapCache;
import ch.ranil.android.flageo.model.Flag;
import ch.ranil.android.flageo.model.Quiz;
import ch.ranil.android.flageo.model.QuizBuilder;
import ch.ranil.android.flageo.utils.UiUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizListener} interface
 * to handle interaction events.
 */
public class Name2FlagQuizFragment extends Fragment {

    private static final String PARAM_NUMBER_OF_CHOICES = "numberOfChoices";

    private static final int WRONG_PENALTY = -1000;

    @Bind(R.id.txt_flagAsked)
    TextView flagAsked;

    private int numberOfChoices = 4;
    private Quiz<Flag> quiz;
    private QuizListener quizListener;
    private ImageButton[] flagButtons;

    private int wrongCounter;

    /**
     * Fragment construction helper.
     *
     * @param numberOfChoices number of flag choices
     * @return fragment instance
     */
    public static Name2FlagQuizFragment newInstance(int numberOfChoices) {
        Name2FlagQuizFragment fragment = new Name2FlagQuizFragment();
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

        View fragmentLayout = inflater.inflate(R.layout.fragment_name2flag_quiz, container, false);
        ButterKnife.bind(this, fragmentLayout);

        flagButtons = new ImageButton[numberOfChoices];

        // TODO add dynamically to layout
        flagButtons[0] = (ImageButton) fragmentLayout.findViewById(R.id.btn_flag1);
        flagButtons[1] = (ImageButton) fragmentLayout.findViewById(R.id.btn_flag2);
        flagButtons[2] = (ImageButton) fragmentLayout.findViewById(R.id.btn_flag3);
        flagButtons[3] = (ImageButton) fragmentLayout.findViewById(R.id.btn_flag4);

        flagAsked.setText(quiz.getAnswer().getTranslation());

        for (int i = 0; i < flagButtons.length; i++) {
            Flag f = quiz.getOption(i);
            BitmapCache.getInstance().loadBitmap(f.getDrawable(), flagButtons[i]);
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
        boolean correct = quiz.isCorrect(answer);
        if (!correct) {
            UiUtils.shakeView(flagButtons[answer]);
            quizListener.timeBoost(++wrongCounter * WRONG_PENALTY);
        } else {
            quizListener.quizAnswered(true);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof QuizListener) {
            quizListener = (QuizListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement QuizListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        quizListener = null;
    }

}
