package ch.ranil.android.flageo.utils;

import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class UiUtils {

    private UiUtils() {}

    /**
     * Flashes the background of the given view in the given transition drawable.
     * @param view view
     * @param drawable transition drawable
     */
    public static void flashView(final View view, int drawable) {

        TransitionDrawable flash = (TransitionDrawable) ContextCompat.getDrawable(view.getContext(), drawable);
        if (flash != null) {
            view.setBackground(flash);
            flash.startTransition(150);
            flash.reverseTransition(150);
        }
    }

    /**
     * Shake the given view.
     *
     * @param view to shake
     */
    public static void shakeView(final View view) {

        shakeView(view, null);
    }

    /**
     * Shake the given view, triggers the given callback listener.
     *
     * @param view to shake
     * @param callback callback
     */
    public static void shakeView(final View view, Animation.AnimationListener callback) {

        Animation shake = new TranslateAnimation(0.0f, 10.0f, 0.0f, 0.0f);
        shake.setDuration(100);
        shake.setInterpolator(new AccelerateDecelerateInterpolator());
        shake.setRepeatCount(5);
        shake.setRepeatMode(Animation.REVERSE);

        if (callback != null) {
            shake.setAnimationListener(callback);
        }

        view.startAnimation(shake);
    }
}
