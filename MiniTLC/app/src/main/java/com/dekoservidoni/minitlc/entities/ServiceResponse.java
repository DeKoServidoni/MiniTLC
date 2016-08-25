package com.dekoservidoni.minitlc.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to hold all the event information
 *
 * Created by DeKoServidoni on 2/25/16.
 */
public class ServiceResponse {

    /** Response information */
    private boolean mSuccess = false;
    private List<MiniEvent> mContent = new ArrayList<>();

    /**
     * Set the content from service
     *
     * @param content list
     */
    public void setContent(List<MiniEvent> content) {
        mContent.clear();
        mContent.addAll(content);
    }

    /**
     * Get the content from service
     *
     * @return content list
     */
    public List<MiniEvent> getContent() {
        return mContent;
    }

    /**
     * Set the success flag
     *
     * @param success flag to indicate if the request is good or not
     */
    public void setSuccess(boolean success) {
        mSuccess = success;
    }

    /**
     * Get the success flag
     *
     * @return true if success, false otherwise
     */
    public boolean isSuccess() {
        return mSuccess;
    }
}
