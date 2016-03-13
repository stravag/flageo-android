package ch.ranil.android.flageo.utils;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

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

    public static void shakeView(final View view) {

        shakeView(view, null);
    }

    public static void shakeView(final View view, Animation.AnimationListener callback) {

        Animation shake = new TranslateAnimation(0.0f, 10.0f, 0.0f, 0.0f);
        shake.setDuration(100);
        shake.setInterpolator(new AccelerateDecelerateInterpolator());
        shake.setRepeatCount(5);
        shake.setRepeatMode(Animation.REVERSE);

        if (callback != null) {
            shake.setAnimationListener(callback);
        }

        /*
        Animation shake = new RotateAnimation(-5.0f, 5.0f, 0.5f, 0.5f);
        shake.setDuration(100);
        shake.setRepeatCount(5);
        shake.setRepeatMode(Animation.REVERSE);
        */

        view.startAnimation(shake);
    }
}
