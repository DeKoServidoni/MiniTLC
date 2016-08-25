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
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_EVENTS = "mini_tlc_events";
    public static final String COLUMN_ID = "event_id";
    public static final String COLUMN_TITLE = "event_name";
    public static final String COLUMN_DESCRIPTION = "event_description";
    public static final String COLUMN_LOCAL = "event_local";
    public static final String COLUMN_WHEN = "event_when";
    public static final String COLUMN_HOUR = "event_hour";

    public static final String CREATE_TABLE_EVENTS = "create table "
            + TABLE_EVENTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text, "
            + COLUMN_DESCRIPTION + " text, "
            + COLUMN_LOCAL + " text, "
            + COLUMN_WHEN + " text, "
            + COLUMN_HOUR + " text);";

    /** Bundle tags */
    public static final String EXTRA_PICTURE_PATH = "extra_picture_path";

    /** Activity request codes */
    public static final int REQUEST_OPEN_GALLERY = 1;
    public static final int REQUEST_OPEN_CAMERA = 1;

    /** Camera preview constants */
    public static final String PICTURE_EXTENSION = ".jpeg";
    public static final String PICTURE_NAME = "MiniTLC Photo";
    public static final String PICTURE_MIME_TYPE = "image/jpeg";

    /** Talk with us constants */
    public static final String EMAIL_TYPE = "plain/text";
    public static final String EMAIL_COORDINATION = "mini@tlccampinas.com.br";
    public static final String EMAIL_CHOOSER_TITLE = "Enviar e-mail...";

    /** Network service URLs */
    public static final String SERVICE_VERSION = "v1";
    public static final String URL_EVENTS_GET_ALL = "https://tlc-api.herokuapp.com/"+SERVICE_VERSION+"/events";

    /** JSON Tags */
    public static final String JSON_ARRAY_EVENTS = "events";
    public static final String JSON_TAG_TITLE = "title";
    public static final String JSON_TAG_DESC = "desc";
    public static final String JSON_TAG_LOCAL = "local";
    public static final String JSON_TAG_WHEN = "when";
    public static final String JSON_TAG_HOUR = "hour";
}
