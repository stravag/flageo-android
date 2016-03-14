package ch.ranil.android.flageo.model;

import junit.framework.Assert;

import org.junit.Test;

public class FlagTest {

    @Test
    public void checkBoost() {
        for (Flag flag : Flag.values()) {
            System.out.println("" + flag + " " + flag.getTimeBoost());
            Assert.assertTrue(flag.getTimeBoost() <= 10000);
        }
    }
}
