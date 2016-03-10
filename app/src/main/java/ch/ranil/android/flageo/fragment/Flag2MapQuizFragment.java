package ch.ranil.android.flageo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.cache.BitmapCache;
import ch.ranil.android.flageo.model.Flag;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizAnswerListener} interface
 * to handle interaction events.
 */
public class Flag2MapQuizFragment extends Fragment {

    @Bind(R.id.txt_flagAsked)
    ImageView flagView;

    @Bind(R.id.map)
    MapView mapView;

    private GoogleMap map;
    private Flag flag;
    private QuizAnswerListener answerListener;

    /**
     * Fragment construction helper.
     *
     * @return fragment instance
     */
    public static Flag2MapQuizFragment newInstance() {
        return new Flag2MapQuizFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Flag[] flags = Flag.values();
        flag = flags[new Random().nextInt(flags.length - 1)];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_flag2map_quiz, container, false);
        ButterKnife.bind(this, fragmentLayout);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });

        BitmapCache.getInstance().loadBitmap(flag.getDrawable(), flagView);

        return fragmentLayout;
    }

    /**
     * Check if the selected answer is correct and send info back to activity.
     *
     * @param answer selected answer
     */
    private void processAnswer(int answer) {
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

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
