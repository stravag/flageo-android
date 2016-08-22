package ch.ranil.android.flageo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.ranil.android.flageo.fragment.MainFragment;
import ch.ranil.android.flageo.model.Difficulty;
import ch.ranil.android.flageo.storage.FlageoStorage;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onResume() {
        viewPager.getAdapter().notifyDataSetChanged();
        super.onResume();
    }
}

class PagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(Difficulty.values()[position]);
    }

    @Override
    public int getCount() {
        int active = 0;
        for (Difficulty difficulty : Difficulty.values()) {
            if (FlageoStorage.isDifficultyActive(difficulty, context)) {
                active++;
            }
        }
        return active;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Difficulty difficulty = Difficulty.values()[position];
        return context.getString(difficulty.getTranslation());
    }

}
