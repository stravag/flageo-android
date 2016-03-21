package ch.ranil.android.flageo.model;

import junit.framework.Assert;

import org.junit.Test;

public class FlagTest {

    @Test
    public void checkBoost() {

        final long MAX_BOOST_EXPECTED = 10000;
        long maxBoost = 0L;

        for (Flag flag : Flag.values()) {
            System.out.println("" + flag + " " + flag.getTimeBoost());
            maxBoost = Math.max(maxBoost, flag.getTimeBoost());
            Assert.assertTrue(flag.getTimeBoost() <= MAX_BOOST_EXPECTED);
        }

        Assert.assertEquals(MAX_BOOST_EXPECTED, maxBoost);
    }
}
