package com.dekoservidoni.minitlc.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.dekoservidoni.minitlc.NetworkTask;
import com.dekoservidoni.minitlc.entities.MiniEvent;
import com.dekoservidoni.minitlc.utils.AppConstants;

import java.util.List;

/**
 * Class responsible to manage the network calls
 * of the application
 *
 * Created by DeKoServidoni on 17/08/16.
 */
public class NetworkManager {

    /**
     * Get the events from service
     *
     * @param callback reference
     */
    public void getEvents(Context context, NetworkCallback callback) {
        new NetworkTask(callback).inContext(context).setUrl(AppConstants.URL_EVENTS_GET_ALL)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Verify if there is internet connection
     *
     * @param context of the application
     *
     * @return true if yes, false otherwise
     */
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /// Callback interface

    /**
     * Interface to communicate with the caller activity
     */
    public interface NetworkCallback {
        /**
         * Called after get the events
         *
         * @param success flag to indicate if the service response is good or not
         * @param miniEvents content from service
         */
        void onGetEventResponse(boolean success, List<MiniEvent> miniEvents);
    }
}
