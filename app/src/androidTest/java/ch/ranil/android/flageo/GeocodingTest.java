package ch.ranil.android.flageo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ch.ranil.android.flageo.model.Flag;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class GeocodingTest extends ActivityTestCase {

    private Context context;
    private Geocoder geocoder;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getTargetContext();
        geocoder = new Geocoder(context, Locale.ENGLISH);
    }

    @Test
    public void testFlagGeocoding() throws Exception {

        List<Address> address = new ArrayList<>();
        for (Flag flag : Flag.values()) {
            if (flag.getMapName(context).isPresent()) {
                try {
                    address = geocoder.getFromLocationName(flag.getMapName(context).get() + " country", 1);
                    address = geocoder.getFromLocation(address.get(0).getLatitude(), address.get(0).getLongitude(), 1);
                    Assert.assertEquals(flag.getMapName(context).get(), address.get(0).getCountryName());
                } catch (IndexOutOfBoundsException e) {
                    // this testing method cannot deal with cyprus, tested it manually
                    if (flag != Flag.CYPRUS) {
                        throw new Exception(flag + ": " + address.toString(), e);
                    }
                }
            }
        }
    }
}
