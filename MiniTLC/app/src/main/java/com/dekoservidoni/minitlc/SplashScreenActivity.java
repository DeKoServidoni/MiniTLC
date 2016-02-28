package com.dekoservidoni.minitlc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.dekoservidoni.minitlc.entities.MiniEvent;
import com.dekoservidoni.minitlc.managers.MiniDatabaseManager;
import com.dekoservidoni.minitlc.utils.AppConstants;
import com.dekoservidoni.minitlc.utils.MiniLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Initial screen of the application.
 *
 * Created by DeKoServidoni on 2/21/16.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //TODO: Initially only. This will be different in the other versions!
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean firstTime = prefs.getBoolean(AppConstants.SHARED_PREFS_FIRST_EXECUTION, true);
        if(firstTime) {
            MiniLog.d("Open SplashScreen, and insert events into database");
            MiniDatabaseManager manager = new MiniDatabaseManager(this);
            manager.insertAll(loadJSONFromAsset());
        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        }, AppConstants.SPLASH_TIMEOUT);
    }

    /**
     * TODO: Initially only. This will be different in the other versions!
     *
     * Read and parse the JSON file from assets
     *
     * @return list of events
     */
    private List<MiniEvent> loadJSONFromAsset() {

        List<MiniEvent> events = new ArrayList<>();
        String json;
        InputStream is;

        try {
            is = getAssets().open("document.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            int total = is.read(buffer);
            MiniLog.d("Read a total of " + total + " bytes");

            is.close();

            json = new String(buffer, "UTF-8");

            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray(AppConstants.TAG_EVENTS);

            for(int i=0 ; i<array.length() ; i++) {
                JSONObject item = array.getJSONObject(i);

                MiniEvent event = new MiniEvent();
                event.setTitle(item.getString(AppConstants.TAG_TITLE));
                event.setDescription(item.getString(AppConstants.TAG_DESCRIPTION));
                event.setDate(item.getString(AppConstants.TAG_DATE));

                events.add(event);
            }

        } catch (IOException ex) {
            MiniLog.e("Failed to read from file: "+ex.getLocalizedMessage());
        } catch (JSONException ex) {
            MiniLog.e("Failed to read JSON: "+ex.getLocalizedMessage());
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean(AppConstants.SHARED_PREFS_FIRST_EXECUTION, false).apply();

        return events;
    }
}
