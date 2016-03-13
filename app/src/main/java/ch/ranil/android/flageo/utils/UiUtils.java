package ch.ranil.android.flageo.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import ch.ranil.android.flageo.R;

public class UiUtils {

    public static void flashView(final View view) {

        Animation flash = new AlphaAnimation(0.0f, 1.0f);
        flash.setDuration(100);
        flash.setInterpolator(new LinearInterpolator());
        flash.setRepeatCount(1);
        flash.setRepeatMode(Animation.REVERSE);
        flash.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.setVisibility(View.VISIBLE);
        view.startAnimation(flash);
    }
}
