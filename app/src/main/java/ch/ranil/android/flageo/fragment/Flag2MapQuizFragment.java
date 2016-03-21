package ch.ranil.android.flageo.fragment;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.cache.BitmapCache;
import ch.ranil.android.flageo.model.Flag;
import ch.ranil.android.flageo.model.FlagQuizBuilder;
import ch.ranil.android.flageo.utils.FlipAnimation;
import ch.ranil.android.flageo.utils.UiUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizListener} interface
 * to handle interaction events.
 */
public class Flag2MapQuizFragment extends Fragment {

    private static final String TAG = "Flag2MapQuizFragment";

    private static final int MAX_WRONG_COUNTER = 2;
    private static final int WRONG_PENALTY = -1000;

    @Bind(R.id.flag_container)
    View flagContainer;

    @Bind(R.id.flash)
    View flashView;

    @Bind(R.id.imgbtn_flagAsked)
    ImageButton flagView;

    @Bind(R.id.btn_flagAsked)
    Button flagTextView;

    @Bind(R.id.map)
    MapView mapView;

    private GoogleMap map;
    private CameraPosition startingPosition;
    private Geocoder geocoder;
    private Flag flag;
    private QuizListener quizListener;

    private int wrongCounter;

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

        try {
            flag = FlagQuizBuilder.getInstance().nextUnasked();
        } catch (FlagQuizBuilder.NothingToQuizException e) {
            quizListener.answeredAllQuestions();
            return;
        }

        geocoder = new Geocoder(getActivity(), Locale.ENGLISH);
        quizListener.timeBoost(flag.getTimeBoost());
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
                startingPosition = map.getCameraPosition();
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(final LatLng latLng) {
                        new Runnable() {
                            @Override
                            public void run() {
                                handleMapLongPress(latLng);
                            }
                        }.run();
                    }
                });
            }
        });

        if (flag != null) {
            BitmapCache.getInstance().loadBitmap(flag.getDrawable(), flagView);
            flagTextView.setText(flag.getTranslation());
        }

        return fragmentLayout;
    }

    public void loadQuiz() {

        wrongCounter = 0;

        try {
            flag = FlagQuizBuilder.getInstance().nextUnasked();
        } catch (FlagQuizBuilder.NothingToQuizException e) {
            quizListener.answeredAllQuestions();
            return;
        }

        map.animateCamera(CameraUpdateFactory.newCameraPosition(startingPosition), 500, null);

        flagView.setVisibility(View.VISIBLE);
        flagTextView.setVisibility(View.INVISIBLE);

        BitmapCache.getInstance().loadBitmap(flag.getDrawable(), flagView);
        flagTextView.setText(flag.getTranslation());
    }

    private void handleMapLongPress(LatLng latLng) {
        try {
            List<Address> location = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            processAnswer(location.get(0).getCountryName());
        } catch (IOException e) {
            Toast.makeText(getActivity(), "Geocoding error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IndexOutOfBoundsException e) {
            // Selected location on map without an address
            // if this is ever a real country we're officially f*cked
            processAnswer("Donaldtrumpia");
        }
    }

    /**
     * Check if the selected answer is correct and send info back to activity.
     *
     * @param countryName selected country
     */
    private void processAnswer(String countryName) {
        Log.d(TAG, countryName);
        boolean correct = flag.getMapName(getActivity()).equals(countryName);
        if (!correct) {
            UiUtils.flashView(flashView, R.drawable.flash_red);
            quizListener.timeBoost(++wrongCounter * WRONG_PENALTY);
            if (wrongCounter >= MAX_WRONG_COUNTER) {
                quizListener.quizAnswered(false);
            }
        } else {
            UiUtils.flashView(flashView, R.drawable.flash_green);
            quizListener.quizAnswered(true);
        }
    }

    @OnClick(R.id.imgbtn_flagAsked)
    public void flipFlagImage() {
        flagContainer.startAnimation(new FlipAnimation(flagView, flagTextView));
    }

    @OnClick(R.id.btn_flagAsked)
    public void flipFlagText() {
        flagContainer.startAnimation(new FlipAnimation(flagTextView, flagView));
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
