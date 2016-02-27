package com.dekoservidoni.minitlc.customs;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import com.dekoservidoni.minitlc.utils.MiniLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Class responsible to implement a custom option for the surface view
 * necessary to preview the camera
 *
 * Created by DeKoServidoni on 2/26/16.
 */
public class CameraSurfaceView extends SurfaceView implements OnTouchListener {

    /** Mask component */
    private Bitmap mMaskBitmap;

    /** Flag indicating to draw or not the mask */
    private boolean mDrawMask = false;

    /** Positioning variables */
    private float mX, mY;

    /** Picture path */
    private String mPicturePath;

    /**
     * Constructor
     *
     * @param context
     *          Application context
     * @param attrs
     *          View attributes
     * @param defStyle
     *          View style
     */
    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    /**
     * Constructor
     *
     * @param context
     *          Application context
     * @param attrs
     *          View attributes
     */
    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * Constructor
     *
     * @param context
     *          Application context
     */
    public CameraSurfaceView(Context context) {
        super(context);
        initView();
    }

    /**
     * Set the picture path
     */
    public void setPicturePath(String path) {
        mPicturePath = path;
    }

    /**
     * Initialize the view
     */
    private void initView() {
        mX = this.getWidth()/2;
        mY = this.getHeight()/2;

        this.setOnTouchListener(this);
        this.setDrawingCacheEnabled(true);
    }

    /**
     * Add the mask to the picture
     *
     * @param resource
     *          Bitmap of the selected mask
     */
    public void addMask(Bitmap resource) {
        mDrawMask = true;
        mMaskBitmap = resource;
        this.invalidate();
    }

    /**
     * Save the new profile picture of the user
     */
    public boolean savePicture() {

        boolean result = false;

        try {

            StringBuilder builder = new StringBuilder(mPicturePath);
            builder.append(Calendar.getInstance().getTimeInMillis());
            builder.append(".jpeg");

            Bitmap bitmap = Bitmap.createBitmap( this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            this.draw(canvas);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(builder.toString())));
            result = addImageToGallery(builder.toString());

        } catch (FileNotFoundException e) {
            MiniLog.e("Exception: " + e.getLocalizedMessage());
        }

        return result;
    }

    /**
     * Add image to the gallery database
     *
     * @param path image
     *
     * @return true if it's OK, false otherwise
     */
    private boolean addImageToGallery(String path) {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "MiniTLC Avatar");
        values.put(MediaStore.Images.Media.DESCRIPTION, "MiniTLC Avatar!");
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, path);

        return (getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) != null);
    }

    /*
     * (non-Javadoc)
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mDrawMask && mMaskBitmap != null) {
            canvas.drawBitmap(mMaskBitmap, mX - (mMaskBitmap.getWidth() / 2), mY - (mMaskBitmap.getHeight() / 2), null);
        }
    }

    /*
     * (non-Javadoc)
     * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if( (event.getAction() == MotionEvent.ACTION_DOWN)
                || (event.getAction() == MotionEvent.ACTION_MOVE) ) {

            mX = event.getX();
            mY = event.getY();
            this.invalidate();
        }

        return true;
    }
}
