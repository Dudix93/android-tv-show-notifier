package com.example.android_tv_show_notifier.activities;

import android.os.Bundle;
import com.example.android_tv_show_notifier.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.android_tv_show_notifier.adapters.FavouritesPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FavouriteActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites_tab_layout);
        tabLayout = findViewById(R.id.favourites_tab_layout);
        viewPager = findViewById(R.id.favourites_view_pager);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.favourite_titles)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.favourite_actors)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final FavouritesPagerAdapter adapter = new FavouritesPagerAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
