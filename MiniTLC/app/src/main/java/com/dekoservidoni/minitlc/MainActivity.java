package com.dekoservidoni.minitlc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dekoservidoni.minitlc.adapters.MiniListAdapter;
import com.dekoservidoni.minitlc.dialogs.AboutDialog;
import com.dekoservidoni.minitlc.entities.MiniEvent;
import com.dekoservidoni.minitlc.managers.NetworkManager;
import com.dekoservidoni.minitlc.utils.AppConstants;

import java.util.List;

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
    @Bind(R.id.activity_main_empty) TextView mEmpty;
    @Bind(R.id.activity_main_progress) ProgressBar mProgress;

    /** List adapter instance */
    private MiniListAdapter mAdapter = null;

    /** Network manager */
    private NetworkManager mManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mManager = new NetworkManager();

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

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgress.setVisibility(View.VISIBLE);
        mEmpty.setVisibility(View.GONE);
        mList.setVisibility(View.GONE);

        if(mManager.isNetworkAvailable(this)) {
            mManager.getEvents(this, new NetworkManager.NetworkCallback() {
                @Override
                public void onGetEventResponse(boolean success, List<MiniEvent> miniEvents) {
                    mProgress.setVisibility(View.GONE);

                    if (success) {
                        mAdapter.setContent(miniEvents);
                        mEmpty.setVisibility(View.GONE);
                        mList.setVisibility(View.VISIBLE);
                    } else {
                        mEmpty.setVisibility(View.VISIBLE);
                        mEmpty.setText(getString(R.string.empty_events));
                        mList.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            mEmpty.setVisibility(View.VISIBLE);
            mEmpty.setText(getString(R.string.no_network));
            mList.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent intent;

        switch(item.getItemId()) {

            case R.id.nav_calendar:
                //TODO
                break;

            case R.id.nav_gallery:
                //TODO
                break;

            case R.id.nav_camera:
                Intent camera = new Intent(this, SelectPictureActivity.class);
                startActivity(camera);
                break;

            case R.id.nav_talk_with_us:
                intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType(AppConstants.EMAIL_TYPE);
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{AppConstants.EMAIL_COORDINATION});
                startActivity(Intent.createChooser(intent, AppConstants.EMAIL_CHOOSER_TITLE));
                break;

            case R.id.nav_about:
                DialogFragment dialog = AboutDialog.newInstance();
                dialog.show(getSupportFragmentManager(), AboutDialog.class.getSimpleName());
                break;


        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        item.setChecked(false);

        return true;
    }

    /**
     * Handle the back button action
     *  - Verify if the menu is open. If true we close it, otherwise we get
     *    out from the app
     */
    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
