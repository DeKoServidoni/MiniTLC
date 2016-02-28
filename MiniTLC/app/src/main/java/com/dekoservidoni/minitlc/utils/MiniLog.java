package com.dekoservidoni.minitlc.utils;

import android.util.Log;

/**
 * Class responsible to hold the log information of the application
 *
 * Created by DeKoServidoni on 2/21/16.
 */
public class MiniLog {

    /**
     * Show the debug log
     *
     * @param message
     *          log message
     */
    public static void d(String message) {
        if(AppConstants.DEBUG_ENABLE) {
            Log.d(AppConstants.TAG, message);
        }
    }

    /**
     * Show the error log
     *
     * @param message
     *          log message
     */
    public static void e(String message) {
        if(AppConstants.DEBUG_ENABLE) {
            Log.e(AppConstants.TAG, message);
        }
    }
}
