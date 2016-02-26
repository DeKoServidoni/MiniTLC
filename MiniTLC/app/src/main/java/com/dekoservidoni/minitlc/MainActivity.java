package com.dekoservidoni.minitlc;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.dekoservidoni.minitlc.adapters.MiniListAdapter;
import com.dekoservidoni.minitlc.managers.MiniDatabaseManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Main screen of the application
 *
 * Created by DeKoServidoni on 2/21/16.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /** UI Components */
    @Bind(R.id.activity_main_drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.activity_main_navigation_view) NavigationView mNavigationView;
    @Bind(R.id.activity_main_toolbar) Toolbar mToolbar;
    @Bind(R.id.activity_main_list) ListView mList;

    /** List adapter instance */
    private MiniListAdapter mAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // setup the toolbar
        setSupportActionBar(mToolbar);

        // setup list and adapters
        mAdapter = new MiniListAdapter(this);
        mList.setAdapter(mAdapter);

        // setup navigation bar
        mNavigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this
                ,mDrawerLayout,mToolbar
                ,R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // not used
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // not used
            }
        };

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();

        MiniDatabaseManager manager = new MiniDatabaseManager(this);
        mAdapter.setContent(manager.getAll());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //TODO
        return true;
    }

    /**
     * Handle the back button action
     *  - Verify if the menu is open. If true we close it, otherwise we get
     *    out from the app
     */
    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
}
