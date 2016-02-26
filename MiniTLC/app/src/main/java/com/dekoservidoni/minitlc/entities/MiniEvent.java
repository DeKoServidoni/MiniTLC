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
    private String mDate = "";

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
     * @param date string
     */
    public void setDate(String date) {
        mDate = date;
    }

    /**
     * Get the event date
     *
     * @return date string
     */
    public String getDate() {
        return mDate;
    }
}
