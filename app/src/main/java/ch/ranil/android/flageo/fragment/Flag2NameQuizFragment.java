package ch.ranil.android.flageo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.cache.BitmapCache;
import ch.ranil.android.flageo.model.Difficulty;
import ch.ranil.android.flageo.model.Flag;
import ch.ranil.android.flageo.model.Quiz;
import ch.ranil.android.flageo.model.FlagQuizBuilder;
import ch.ranil.android.flageo.utils.UiUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizListener} interface
 * to handle interaction events.
 */
public class Flag2NameQuizFragment extends Fragment {

    private static final String PARAM_NUMBER_OF_CHOICES = "numberOfChoices";
    private static final String PARAM_DIFFICULTY = "difficulty";

    @Bind(R.id.txt_flagAsked)
    ImageView flagAsked;

    private int numberOfChoices = 4;
    private Difficulty difficulty = Difficulty.EASY;
    private Quiz<Flag> quiz;
    private QuizListener quizListener;
    private Button[] flagButtons;

    private int wrongCounter;

    /**
     * Fragment construction helper.
     *
     * @param numberOfChoices number of flag choices
     * @return fragment instance
     */
    public static Flag2NameQuizFragment newInstance(int numberOfChoices, Difficulty difficulty) {
        Flag2NameQuizFragment fragment = new Flag2NameQuizFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_NUMBER_OF_CHOICES, numberOfChoices);
        args.putSerializable(PARAM_DIFFICULTY, difficulty);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            numberOfChoices = getArguments().getInt(PARAM_NUMBER_OF_CHOICES);
            difficulty = (Difficulty) getArguments().getSerializable(PARAM_DIFFICULTY);
        }

        try {
            quiz = FlagQuizBuilder.getInstance().buildQuiz(numberOfChoices, difficulty);
        } catch (FlagQuizBuilder.NothingToQuizException e) {
            quizListener.answeredAllQuestions();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_flag2name_quiz, container, false);
        ButterKnife.bind(this, fragmentLayout);

        flagButtons = new Button[numberOfChoices];

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

    @Override
    public void onStart() {
        super.onStart();
        // start timer when fragment is ready
        quizListener.timeBoost(0);
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
            quizListener.timeBoost(++wrongCounter * difficulty.getPenalty());
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
}
