package com.dekoservidoni.minitlc.managers;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.widget.FrameLayout;

import com.dekoservidoni.minitlc.customs.CameraPreviewView;
import com.dekoservidoni.minitlc.utils.MiniLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Class responsible to manage all the camera operations
 *
 * Created by DeKoServidoni on 2/26/16.
 */
@SuppressWarnings("deprecation")
public class MiniCameraManager  {

    /** Container view */
    private FrameLayout mSurfaceView = null;

    /** Application context */
    private Context mContext = null;

    /** Camera instance */
    private Camera mCamera = null;

    /** Callback instance */
    private MiniCameraCallback mCallback = null;

    /**
     * Interface responsible to send the status back to the caller
     */
    public interface MiniCameraCallback {
        void onTakePictureFinished(String picturePath);
    }

    /**
     * Constructor
     *
     * @param context
     *          Application context
     * @param surfaceView
     *          Frame to preview the camera
     * @param callback
     *          Callback instance
     */
    public MiniCameraManager(Context context, FrameLayout surfaceView, MiniCameraCallback callback) {
        mSurfaceView = surfaceView;
        mContext = context;
        mCallback = callback;
    }

    /**
     * Start the camera previw
     */
    public void startCamera() {

        try {
            if(mCamera != null) {
                mCamera.startPreview();
            } else {

                // attempt to get a Camera instance
                int number = Camera.getNumberOfCameras();
                mCamera = Camera.open(number > 0 ? 1 : 0);

                CameraPreviewView preview = new CameraPreviewView(mContext, mCamera);
                mSurfaceView.addView(preview);
            }
        }
        catch (Exception e){
            MiniLog.e("Error starting camera preview: " + e.getMessage());
        }
    }

    /**
     * Stop the camera preview
     */
    public void stopCamera() {

        if(mCamera != null) {
            mCamera.stopPreview();
        }
    }

    /**
     * Release the camera
     */
    public void release() {
        stopCamera();

        if(mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * Take picture
     */
    public void takePicture() {
        Camera.Parameters params = mCamera.getParameters();
        params.setRotation(270);
        mCamera.setParameters(params);
        mCamera.takePicture(null, null, mPicture);
    }

    /**
     * Handle the picture after it been taken
     */
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile();
            String path = "";

            try {

                if(pictureFile != null) {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();

                    path = pictureFile.getAbsolutePath();
                }

            } catch (FileNotFoundException e) {
                MiniLog.d("File not found: " + e.getMessage());
            } catch (IOException e) {
                MiniLog.d("Error accessing file: " + e.getMessage());
            }

            mCallback.onTakePictureFinished(path);
        }
    };

    /**
     * Create a File for saving an image or video
     */
    private File getOutputMediaFile(){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                , "MiniTLCPics");

        if (! mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                MiniLog.d("Failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
    }
}
