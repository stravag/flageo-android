package ch.ranil.android.flageo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import ch.ranil.android.flageo.R;

public class UiUtils {

    private UiUtils() {
    }

    /**
     * Flashes the background of the given view in the given drawable.
     *
     * @param view            view
     * @param drawable        transition drawable
     */
    public static void flashView(final View view, int drawable) {

        Context context = view.getContext();

        Drawable current = view.getBackground();
        if (current == null) {
            current = ContextCompat.getDrawable(context, android.R.color.transparent);
        }

        Drawable[] drawables = {current, ContextCompat.getDrawable(context, drawable)};
        TransitionDrawable flash = new TransitionDrawable(drawables);

        view.setBackground(flash);
        flash.startTransition(200);
        flash.reverseTransition(200);
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
     * @param view     to shake
     * @param callback callback
     */
    public static void shakeView(final View view, Animation.AnimationListener callback) {

        flashView(view, R.color.red_flash);

        Animation shake = new TranslateAnimation(-10.0f, 10.0f, 0.0f, 0.0f);
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
