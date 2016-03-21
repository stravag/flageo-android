package ch.ranil.android.flageo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ch.ranil.android.flageo.model.Flag;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class GeocodingTest {

    private Context context;
    private Geocoder geocoder;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getContext();
        geocoder = new Geocoder(context, Locale.ENGLISH);
    }

    @Test
    @Ignore
    public void testFlagGeocoding() throws IOException {
        for (Flag flag : Flag.values()) {
            List<Address> address = geocoder.getFromLocationName(flag.getMapName(context), 1);
            Assert.assertEquals(flag.getMapName(context), address.get(0).getCountryName());
        }
    }
}
