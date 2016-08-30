package com.dekoservidoni.minitlc.utils;

/**
 * Class responsible to hold all the application constants
 *
 * Created by DeKoServidoni on 2/21/16.
 */
public class AppConstants {

    /** Debug constants */
    static final String TAG = "MiniTLC";
    static final boolean DEBUG_ENABLE = true;

    /** Firebase constants */
    public static final String FIREBASE_TABLE_NAME = "mini_events";

    /** Bundle tags */
    public static final String EXTRA_PICTURE_PATH = "extra_picture_path";

    /** Activity request codes */
    public static final int REQUEST_OPEN_CAMERA = 1;

    /** Camera preview constants */
    static final String PICTURE_EXTENSION = ".jpeg";
    static final String PICTURE_NAME = "MiniTLC Photo";
    static final String PICTURE_MIME_TYPE = "image/jpeg";

    /** JSON Tags */
    public static final String JSON_ARRAY_EVENTS = "events";
    public static final String JSON_TAG_TITLE = "title";
    public static final String JSON_TAG_DESC = "desc";
    public static final String JSON_TAG_LOCAL = "local";
    public static final String JSON_TAG_WHEN = "when";
    public static final String JSON_TAG_HOUR = "hour";
}
