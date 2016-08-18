package com.dekoservidoni.minitlc;

import android.content.Context;
import android.os.AsyncTask;

import com.dekoservidoni.minitlc.entities.MiniEvent;
import com.dekoservidoni.minitlc.entities.ServiceResponse;
import com.dekoservidoni.minitlc.managers.NetworkManager;
import com.dekoservidoni.minitlc.utils.AppConstants;
import com.dekoservidoni.minitlc.utils.MiniLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Internal class to execute the network calls in background
 */
public class NetworkTask extends AsyncTask<Void, Void, ServiceResponse> {

    /** Callback reference */
    private NetworkManager.NetworkCallback mCallback;

    /** URL for connection */
    private URL mUrl;

    /** Application context */
    private Context mContext;

    /**
     * Constructor
     *
     * @param callback reference
     */
    public NetworkTask(NetworkManager.NetworkCallback callback) {
        mCallback = callback;
    }

    /**
     * Set the url for the connection
     *
     * @param url to connect
     *
     * @return this object
     */
    public NetworkTask setUrl(String url) {

        try {
            mUrl = new URL(url);
        } catch(MalformedURLException ex) {
            MiniLog.e("" + ex.getLocalizedMessage());
            mUrl = null;
        }

        return this;
    }

    /**
     * Set context
     *
     * @param context to be executed in
     *
     * @return this object
     */
    public NetworkTask inContext(Context context) {
        mContext = context;
        return this;
    }

    @Override
    protected ServiceResponse doInBackground(Void... params) {

        if(mUrl == null) {
            return null;
        }

        HttpURLConnection connection = null;
        ServiceResponse response = null;

        try {
            connection = (HttpURLConnection) mUrl.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());

            // read the input from the connection
            String input = readStream(in);

            // parse the json to create a service object
            response = parseResponse(input);

        } catch(IOException ex) {
            MiniLog.e("" + ex.getLocalizedMessage());
            response = null;
            connection = null;

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(ServiceResponse response) {
        super.onPostExecute(response);

        if(mCallback != null) {
            mCallback.onGetEventResponse(response != null && response.isSuccess(),
                    response != null ? response.getContent() : new ArrayList<MiniEvent>());
        }
    }

    /// Private methods

    /**
     * Parse the JSON response from service
     *
     * @param response from service
     *
     * @return object representation
     */
    private ServiceResponse parseResponse(String response) {
        ServiceResponse serviceResponse = null;

        try {
            JSONObject object = new JSONObject(response);

            JSONArray events = object.getJSONArray(AppConstants.JSON_ARRAY_EVENTS);
            if(events != null) {
                List<MiniEvent> content = new ArrayList<>();

                for(int i=0 ; i<events.length() ; i++) {
                    JSONObject event = events.getJSONObject(i);

                    MiniEvent miniEvent = new MiniEvent();
                    miniEvent.setTitle(event.getString(AppConstants.JSON_TAG_TITLE));
                    miniEvent.setDescription(event.getString(AppConstants.JSON_TAG_DESC));
                    miniEvent.setHour(event.getString(AppConstants.JSON_TAG_HOUR));
                    miniEvent.setWhen(event.getString(AppConstants.JSON_TAG_WHEN));
                    miniEvent.setLocal(event.getString(AppConstants.JSON_TAG_LOCAL));

                    content.add(miniEvent);
                }

                serviceResponse = new ServiceResponse();
                serviceResponse.setSuccess(!content.isEmpty());
                serviceResponse.setContent(content);

                // insert into database
                //MiniDatabaseManager manager = new MiniDatabaseManager(mContext);
                //manager.insertAll(content);
            }

        } catch (JSONException ex) {
            MiniLog.e("" + ex.getLocalizedMessage());
        }
        return serviceResponse;
    }

    /**
     * Read input stream into a string
     *
     * @param inputStream from service
     *
     * @return string representation
     *
     * @throws IOException
     */
    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder result = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }
}
