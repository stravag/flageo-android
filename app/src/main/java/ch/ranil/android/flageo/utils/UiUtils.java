package ch.ranil.android.flageo.utils;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class UiUtils {

    public static void flashView(final View view, int drawable) {

        TransitionDrawable redFlash = (TransitionDrawable) view.getContext().getResources().getDrawable(drawable);
        if (redFlash != null) {
            view.setBackground(redFlash);
            redFlash.startTransition(100);
            redFlash.reverseTransition(100);
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
