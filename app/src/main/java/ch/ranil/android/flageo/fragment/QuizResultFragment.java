package ch.ranil.android.flageo.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.model.Mode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizResultFragment extends Fragment {

    private static final String PARAM_SCORE = "score";
    private static final String PARAM_RECORD = "record";
    private static final String PARAM_MODE = "mode";

    @Bind(R.id.txt_scoreIntro)
    TextView scoreIntro;

    @Bind(R.id.txt_score)
    TextView scoreView;

    @Bind(R.id.txt_comment)
    TextView commentView;

    private int score;
    private int record;
    private Mode mode;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QuizResultFragment.
     */
    public static QuizResultFragment newInstance(int score, int record, Mode mode) {
        QuizResultFragment fragment = new QuizResultFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_SCORE, score);
        args.putInt(PARAM_RECORD, record);
        args.putSerializable(PARAM_MODE, mode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt(PARAM_SCORE);
            record = getArguments().getInt(PARAM_RECORD);
            mode = (Mode) getArguments().getSerializable(PARAM_MODE);
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

        new NumbersApiTask().execute(score);

        return fragmentLayout;
    }

    private class NumbersApiTask extends AsyncTask<Integer, String, String> {

        @Override
        protected String doInBackground(Integer... params) {

            URL url;
            HttpURLConnection conn;

            try {
                url = new URL("http://numbersapi.com/" + params[0]);
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                return "";
            }

            try (InputStream in = new BufferedInputStream(conn.getInputStream())) {
                conn.setRequestMethod("POST");
                return IOUtils.toString(in, "UTF-8");
            } catch (IOException e) {
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            commentView.setText(s);
        }
    }
}
