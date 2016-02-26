package com.dekoservidoni.minitlc.utils;

/**
 * Class responsible to hold all the application constants
 *
 * Created by DeKoServidoni on 2/21/16.
 */
public class AppConstants {

    /** Debug constants */
    public static final String TAG = "MiniTLC";
    public static final boolean DEBUG_ENABLE = true;

    /** Splash screen timeout */
    public static final int SPLASH_TIMEOUT = 3000;

    /** Database constants */
    public static final String DATABASE_NAME = "mini_tlc_database.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_EVENTS = "mini_tlc_events";
    public static final String COLUMN_ID = "event_id";
    public static final String COLUMN_TITLE = "event_title";
    public static final String COLUMN_DESCRIPTION = "event_description";
    public static final String COLUMN_DATE = "event_date";

    public static final String CREATE_TABLE_EVENTS = "create table "
            + TABLE_EVENTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text, "
            + COLUMN_DESCRIPTION + " text, "
            + COLUMN_DATE + " text);";

    /** JSON Tags */
    public static final String TAG_EVENTS = "events";
    public static final String TAG_TITLE = "title";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_DATE = "date";

    /** Flag to indicate if it's first execution or not */
    public static final String SHARED_PREFS_FIRST_EXECUTION = "first_execution";

}
