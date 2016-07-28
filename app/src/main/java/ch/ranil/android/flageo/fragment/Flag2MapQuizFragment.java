package ch.ranil.android.flageo.fragment;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.cache.BitmapCache;
import ch.ranil.android.flageo.model.Difficulty;
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

    private static final String PARAM_DIFFICULTY = "difficulty";

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

    @Bind(R.id.progressBar2)
    ProgressBar progressBar;

    private GoogleMap map;
    private LatLng startingPosition = new LatLng(30, 0);
    private Geocoder geocoder;
    private Flag flag;
    private QuizListener quizListener;
    private Difficulty difficulty;

    private int wrongCounter;

    /**
     * Fragment construction helper.
     *
     * @return fragment instance
     */
    public static Flag2MapQuizFragment newInstance(Difficulty difficulty) {
        Flag2MapQuizFragment fragment = new Flag2MapQuizFragment();
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

        geocoder = new Geocoder(getActivity(), Locale.ENGLISH);
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
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(startingPosition, 1.0f));
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        new GeocodingTask().execute(latLng);
                    }
                });
                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        new GeocodingTask().execute(latLng);
                    }
                });
            }
        });

        loadQuiz();

        return fragmentLayout;
    }

    @Override
    public void onStart() {
        super.onStart();
        // start timer when fragment is ready
        quizListener.timeBoost(0);
    }

    public void loadQuiz() {

        wrongCounter = 0;

        try {
            // some countries cannot be resolved properly on google maps
            do {
                flag = FlagQuizBuilder.getInstance().nextUnasked();
            } while (!flag.getMapName(getContext()).isPresent());

            //quizListener.timeBoost(flag.getTimeBoost()); // small country timeboost disabled
        } catch (FlagQuizBuilder.NothingToQuizException e) {
            quizListener.answeredAllQuestions();
            return;
        }

        if (map != null) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(startingPosition, 1.0f), 500, null);
        }

        flagView.setVisibility(View.VISIBLE);
        flagTextView.setVisibility(View.INVISIBLE);

        BitmapCache.getInstance().loadBitmap(flag.getDrawable(), flagView);
        flagTextView.setText(flag.getTranslation());
    }

    /**
     * Check if the selected answer is correct and send info back to activity.
     *
     * @param countryName selected country
     */
    private void processAnswer(String countryName) {
        Log.d(TAG, "Selected country: " + countryName);
        boolean correct = flag.getMapName(getActivity()).get().equals(countryName);
        if (!correct) {
            wrongCounter++;
            UiUtils.flashView(flashView, R.color.red_flash);
            Toast.makeText(getContext(), getString(R.string.selected_country, countryName), Toast.LENGTH_SHORT).show();
            quizListener.timeBoost(difficulty.getPenalty());
            if (wrongCounter >= difficulty.getMaxwrong()) {
                quizListener.quizAnswered(false);
            }
        } else {
            UiUtils.flashView(flashView, R.color.green_flash);
            quizListener.quizAnswered(true);
        }
    }

    @OnClick(R.id.imgbtn_flagAsked)
    public void flipFlagImage() {
        quizListener.timeBoost(difficulty.getPenalty());
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

    private class GeocodingTask extends AsyncTask<LatLng, String, String> {

        private long startTime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(LatLng... params) {
            Log.d(TAG, "GeocodingTask started...");
            startTime = System.currentTimeMillis();
            LatLng latLng = params[0];
            try {
                List<Address> location = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                return location.get(0).getCountryName();
            } catch (IOException e) {
                return null;
            } catch (IndexOutOfBoundsException e) {
                // Selected location on map without an address
                return getString(R.string.unknown);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, String.format("GeocodingTask finished in %dms", System.currentTimeMillis() - startTime));
            progressBar.setVisibility(View.GONE);
            if (s != null) {
                processAnswer(s);
            } else {
                Toast.makeText(getActivity(), "An error occurred while checking the position, sorry about that.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
