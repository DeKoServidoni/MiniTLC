package com.dekoservidoni.minitlc.entities;

/**
 * Class responsible to hold all the event information
 *
 * Created by DeKoServidoni on 2/25/16.
 */
public class MiniEvent {

    /** Event information */
    private String mTitle = "";
    private String mDescription = "";
    private String mWhen = "";
    private String mHour = "";
    private String mLocal = "";

    /**
     * Set the event title
     *
     * @param title string
     */
    public void setTitle(String title) {
        mTitle = title;
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
     * Set the event description
     *
     * @param description string
     */
    public void setDescription(String description) {
        mDescription = description;
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
     * Set the event date
     *
     * @param when string
     */
    public void setWhen(String when) {
        mWhen = when;
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
     * Set the event hour
     *
     * @param hour string
     */
    public void setHour(String hour) {
        mHour = hour;
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
     * Set the event local
     *
     * @param local string
     */
    public void setLocal(String local) {
        mLocal = local;
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
