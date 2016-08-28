package com.dekoservidoni.minitlc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dekoservidoni.minitlc.R;
import com.dekoservidoni.minitlc.fragments.ListFragment;
import com.dekoservidoni.minitlc.fragments.ProfileFragment;
import com.dekoservidoni.minitlc.utils.AppConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    /** UI Component */
    @Bind(R.id.container) ViewPager mViewPager;
    @Bind(R.id.tab) TabLayout mTabLayout;

    /** Fragments */
    private ListFragment mListFragment = ListFragment.newInstance();
    private ProfileFragment mProfileFragment = ProfileFragment.newInstance();

    /** Class constants */
    private static final int FRAGMENT_QUANTITY = 2;
    private static final int FRAGMENT_LIST = 0;
    private static final int FRAGMENT_PROFILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_events)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_profile)));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // not used
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // not used
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == AppConstants.REQUEST_OPEN_CAMERA) {
            mListFragment.setPictureReturn();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case FRAGMENT_LIST: return mListFragment;
                case FRAGMENT_PROFILE: return mProfileFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            return FRAGMENT_QUANTITY;
        }
    }
}
