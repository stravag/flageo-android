package ch.ranil.android.flageo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.R;
import ch.ranil.android.flageo.cache.BitmapCache;
import ch.ranil.android.flageo.model.Flag;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizListener} interface
 * to handle interaction events.
 */
public class Flag2MapQuizFragment extends Fragment {

    private static final String TAG = "Flag2MapQuizFragment";

    private static final String PARAM_CAMERA_POSITION = "cameraPosition";

    @Bind(R.id.txt_flagAsked)
    ImageView flagView;

    @Bind(R.id.map)
    MapView mapView;

    private GoogleMap map;
    private Geocoder geocoder;
    private Flag flag;
    private QuizListener quizListener;

    /**
     * Fragment construction helper.
     *
     * @return fragment instance
     */
    public static Flag2MapQuizFragment newInstance(CameraPosition cameraPosition) {
        Flag2MapQuizFragment fragment = new Flag2MapQuizFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARAM_CAMERA_POSITION, cameraPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Flag[] flags = Flag.values();
        flag = flags[new Random().nextInt(flags.length - 1)];

        geocoder = new Geocoder(getActivity());
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

                CameraPosition cameraPosition = getArguments().getParcelable(PARAM_CAMERA_POSITION);
                if (cameraPosition != null) {
                    map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        try {
                            List<Address> location = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                            processAnswer(location.get(0));
                        } catch (IOException e) {
                            Toast.makeText(getActivity(), "Geocoding error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {
                        quizListener.cameraPosition(cameraPosition);
                    }
                });
            }
        });

        BitmapCache.getInstance().loadBitmap(flag.getDrawable(), flagView);

        return fragmentLayout;
    }

    /**
     * Check if the selected answer is correct and send info back to activity.
     *
     * @param address selected address
     */
    private void processAnswer(Address address) {
        Log.d(TAG, address.getCountryName());
        boolean correct = address.getCountryName().equals(getString(flag.getTranslation()));
        Toast.makeText(getActivity(), "" + correct, Toast.LENGTH_SHORT).show();
        quizListener.quizAnswered(correct);
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
