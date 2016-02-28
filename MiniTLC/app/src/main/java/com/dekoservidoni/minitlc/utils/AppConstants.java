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

    /** Bundle tags */
    public static final String EXTRA_PICTURE_PATH = "extra_picture_path";

    /** Activity request codes */
    public static final int REQUEST_OPEN_GALLERY = 1;
    public static final int REQUEST_OPEN_CAMERA = 1;
    public static final int REQUEST_OPEN_EDITOR = 10;

    /** Activity response codes */
    public static final int RESPONSE_FROM_EDITOR = 11;

    /** Camera preview constants */
    public static final String PICTURE_EXTENSION = ".jpeg";
    public static final String PICTURE_NAME = "MiniTLC Photo";
    public static final String PICTURE_MIME_TYPE = "image/jpeg";

    /** Talk with us constants */
    public static final String EMAIL_TYPE = "plain/text";
    public static final String EMAIL_COORDINATION = "mini@tlccampinas.com.br";
    public static final String EMAIL_CHOOSER_TITLE = "Enviar e-mail...";












    //TODO: NEED TO REMOVE IN NEWER VERSIONS!
    /** JSON Tags */
    public static final String TAG_EVENTS = "events";
    public static final String TAG_TITLE = "title";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_DATE = "date";

    /** Flag to indicate if it's first execution or not */
    public static final String SHARED_PREFS_FIRST_EXECUTION = "first_execution";

}
