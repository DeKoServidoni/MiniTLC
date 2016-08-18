package com.dekoservidoni.minitlc.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dekoservidoni.minitlc.database.MiniDatabaseHelper;
import com.dekoservidoni.minitlc.entities.MiniEvent;
import com.dekoservidoni.minitlc.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to manage all the database operations of the application
 *
 * Created by DeKoServidoni on 2/25/16.
 */
public class MiniDatabaseManager {

    /** Database helper instance */
    private MiniDatabaseHelper mDatabaseHelper = null;

    /** Database instance */
    private SQLiteDatabase mDatabase = null;

    /**
     * Constructor
     *
     * @param context
     *          Application context
     */
    public MiniDatabaseManager(Context context) {
        mDatabaseHelper = new MiniDatabaseHelper(context);
    }

    /**
     * Open the database connection
     */
    private void open() {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    /**
     * Close the database connection
     */
    private void close() {
        if(mDatabase != null) {
            mDatabase.close();
        }
    }

    /**
     * Insert a list of events in the database
     *
     * @param events
     *          List of events to be inserted
     */
    public void insertAll(List<MiniEvent> events) {
        open();

        for(MiniEvent event : events) {

            ContentValues values = new ContentValues();
            values.put(AppConstants.COLUMN_TITLE, event.getTitle());
            values.put(AppConstants.COLUMN_DESCRIPTION, event.getDescription());
            values.put(AppConstants.COLUMN_HOUR, event.getHour());
            values.put(AppConstants.COLUMN_LOCAL, event.getLocal());
            values.put(AppConstants.COLUMN_WHEN, event.getWhen());

            mDatabase.insert(AppConstants.TABLE_EVENTS, null, values);
        }

        close();
    }

    /**
     * Get a list of events of the database
     *
     * @return list of events saved
     */
    public List<MiniEvent> getAll() {
        open();

        List<MiniEvent> events = new ArrayList<>();

        Cursor cursor = mDatabase.query(AppConstants.TABLE_EVENTS,null,null,null,null,null,null);

        if(cursor != null) {

            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                do {

                    MiniEvent event = new MiniEvent();

                    event.setTitle(cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_TITLE)));
                    event.setDescription(cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_DESCRIPTION)));
                    event.setWhen(cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_WHEN)));
                    event.setLocal(cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_LOCAL)));
                    event.setHour(cursor.getString(cursor.getColumnIndex(AppConstants.COLUMN_HOUR)));

                    events.add(event);

                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        close();

        return events;
    }
}
