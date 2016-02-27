package com.dekoservidoni.minitlc.customs;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dekoservidoni.minitlc.utils.MiniLog;

import java.io.IOException;

/**
 * Class responsible to implement a custom option for the surface view
 * necessary to preview the camera
 *
 * Created by DeKoServidoni on 2/26/16.
 */
@SuppressWarnings("deprecation")
public class CameraPreviewView extends SurfaceView implements SurfaceHolder.Callback {

    /** Surface holder to preview the camera */
    private SurfaceHolder mHolder;

    /** Device camera instance */
    private Camera mCamera;

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
    public CameraPreviewView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Constructor
     *
     * @param context
     *          Application context
     * @param attrs
     *          View attributes
     */
    public CameraPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor
     *
     * @param context
     *          Application context
     * @param camera
     *          Device's camera
     */
    public CameraPreviewView(Context context, Camera camera) {
        super(context);

        mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            MiniLog.e("Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int width, int height) {

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();

        } catch (Exception e){
            MiniLog.e("Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // empty
    }
}
