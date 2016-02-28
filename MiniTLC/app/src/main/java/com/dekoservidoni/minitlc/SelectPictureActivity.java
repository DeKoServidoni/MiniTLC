package com.dekoservidoni.minitlc;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dekoservidoni.minitlc.utils.AppConstants;
import com.dekoservidoni.minitlc.utils.MiniLog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class responsible to present the user two options to select a picture
 * to edit
 *
 * Created by DeKoServidoni on 2/27/16.
 */
public class SelectPictureActivity extends AppCompatActivity {

    /** UI Components */
    @Bind(R.id.select_camera) ImageView mSelectCamera = null;
    @Bind(R.id.select_gallery) ImageView mSelectGallery = null;

    /** Choose types */
    private static final int CHOOSE_GALLERY = 1;
    private static final int CHOOSE_CAMERA = 2;

    /** Flag indicating what option the user chooses */
    private int mChooseType = -1;

    /** Hold the path of the picture from camera */
    private String mGalleryPath = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_picture);
        ButterKnife.bind(this);

        mSelectCamera.setOnClickListener(mSelectActionListener);
        mSelectGallery.setOnClickListener(mSelectActionListener);
    }

    /**
     * Handle the user click action in the buttons of the UI
     */
    private View.OnClickListener mSelectActionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()) {

                case R.id.select_camera:
                    mChooseType = CHOOSE_CAMERA;
                    openCamera();
                    break;

                case R.id.select_gallery:
                    mChooseType = CHOOSE_GALLERY;
                    openGallery();
                    break;

                default:
                    // do nothing
            }
        }
    };

    /**
     * Handle the response of the called activity, so we can go on to the editor screen
     *
     * @param requestCode
     *          Intent request code
     * @param resultCode
     *          Activity result code
     * @param data
     *          Data from called activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            switch (mChooseType) {

                case CHOOSE_CAMERA:
                    handleCameraReturn();
                    break;

                case CHOOSE_GALLERY:
                    handleGalleryReturn(data);
                    break;

                default:
                    // do nothing
            }
    }

    /**
     * Open the gallery for the user choose the picture
     */
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, AppConstants.REQUEST_OPEN_GALLERY);
    }

    /**
     * Handle the data returned from the gallery (picture choose by the user)
     *
     * @param data
     *          Data from gallery
     */
    private void handleGalleryReturn(Intent data) {

        if( (data != null) && (data.getData() != null) ) {
            String path;
            String[] projection = { MediaStore.MediaColumns.DATA };

            Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);

            if(cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                path = filePath;
            } else {
                path = data.getData().getPath();
            }

            MiniLog.d("(GALLERY) Returned path: " + path);

            sendIntent(path);
        }
    }

    /**
     * Open the camera for the user take a picture
     */
    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                MiniLog.e(""+ex.getLocalizedMessage());
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, AppConstants.REQUEST_OPEN_CAMERA);
            }
        }
    }

    /**
     * Handle the data returned from the camera (picture taken by the user)
     */
    private void handleCameraReturn() {

        // save the picture into gallery
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File("file:" + mGalleryPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);

        sendIntent(mGalleryPath);
    }

    /**
     * Create the image file for the camera
     *
     * @return image file
     *
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mGalleryPath = image.getAbsolutePath();
        MiniLog.d("(CAMERA) Returned path: " + mGalleryPath);

        return image;
    }

    /**
     * Send the picture path to the editor screen
     *
     * @param path
     *          Picture path
     */
    private void sendIntent(String path) {
        Intent editorScreen = new Intent(this, EditPictureActivity.class);
        editorScreen.putExtra(AppConstants.EXTRA_PICTURE_PATH, path);
        startActivity(editorScreen);
    }
}
