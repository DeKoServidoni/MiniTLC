package com.dekoservidoni.minitlc.managers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.dekoservidoni.minitlc.activities.EditPictureActivity;
import com.dekoservidoni.minitlc.utils.AppConstants;
import com.dekoservidoni.minitlc.utils.MiniLog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Class responsible to hold all the camera actions and flows
 *
 * Created by DeKoServidoni on 28/08/16.
 */
public class CameraManager {

    /** Application context */
    private Activity mActivity;

    /** Hold the path of the picture from camera */
    private String mGalleryPath = "";

    /**
     * Constructor
     *
     * @param activity of the application
     */
    public CameraManager(Activity activity) {
        mActivity = activity;
    }

    /// Public methods

    /**
     * Open the camera to take the picture
     */
    public void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                MiniLog.e(""+ex.getLocalizedMessage());
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                mActivity.startActivityForResult(takePictureIntent, AppConstants.REQUEST_OPEN_CAMERA);
            }
        }
    }

    /**
     * Handle the data returned from the camera (picture taken by the user)
     */
    public void handleReturn() {

        // save the picture into gallery
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File("file:" + mGalleryPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mActivity.sendBroadcast(mediaScanIntent);

        // open the editor screen
        Intent editorScreen = new Intent(mActivity, EditPictureActivity.class);
        editorScreen.putExtra(AppConstants.EXTRA_PICTURE_PATH, mGalleryPath);
        mActivity.startActivity(editorScreen);
    }

    /// Private methods

    /**
     * Create the image file for the camera
     *
     * @return image file
     *
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir + imageFileName + "mini.jpg");
        boolean result = image.createNewFile();

        mGalleryPath = image.getAbsolutePath();
        MiniLog.d("(CAMERA) Returned path: " + mGalleryPath + " ["+result+"]");

        return image;
    }
}
