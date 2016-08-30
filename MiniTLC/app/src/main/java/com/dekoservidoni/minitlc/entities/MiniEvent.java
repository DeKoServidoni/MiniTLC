package com.dekoservidoni.minitlc.entities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

/**
 * Class responsible to hold all the event information
 *
 * Created by DeKoServidoni on 2/25/16.
 */
public class MiniEvent {

    /** Event information */
    @PropertyName("title")
    private String mTitle = "";
    @PropertyName("description")
    private String mDescription = "";
    @PropertyName("when")
    private String mWhen = "";
    @PropertyName("hour")
    private String mHour = "";
    @PropertyName("local")
    private String mLocal = "";

    /**
     * Constructor
     */
    public MiniEvent() {
        // Firebase:
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    /**
     * Constructor
     *
     * @param title       of event
     * @param description of event
     * @param when        of event
     * @param hour        of event
     * @param local       of event
     */
    public MiniEvent(String title, String description, String when, String hour, String local) {
        mTitle = title;
        mDescription = description;
        mWhen = when;
        mHour = hour;
        mLocal = local;
    }

    /**
     * Get the event title
     *
     * @return title string
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the event description
     *
     * @return description string
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Get the event date
     *
     * @return date string
     */
    public String getWhen() {
        return mWhen;
    }

    /**
     * Get the event hour
     *
     * @return date string
     */
    public String getHour() {
        return mHour;
    }

    /**
     * Get the event local
     *
     * @return date string
     */
    public String getLocal() {
        return mLocal;
    }
}
