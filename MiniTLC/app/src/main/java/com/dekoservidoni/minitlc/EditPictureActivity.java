package com.dekoservidoni.minitlc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dekoservidoni.minitlc.customs.CameraSurfaceView;
import com.dekoservidoni.minitlc.utils.AppConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class responsible to show a camera and build the picture of the user
 *
 * Created by DeKoServidoni on 2/26/16.
 */
public class EditPictureActivity extends AppCompatActivity {

    /** UI Components */
    @Bind(R.id.camera_preview) CameraSurfaceView mCameraPreview;
    @Bind(R.id.camera_add_alegria) FloatingActionButton mMaskBallon;
    @Bind(R.id.camera_add_haroldinho) FloatingActionButton mMaskHaroldinho;
    @Bind(R.id.camera_add_minitlc) FloatingActionButton mMaskMiniTLC;
    @Bind(R.id.camera_cancel) FloatingActionButton mCancel;
    @Bind(R.id.camera_save) FloatingActionButton mSavePicture;

    /** Picture path */
    private String mPath = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_picture);
        ButterKnife.bind(this);

        mMaskBallon.setOnClickListener(mButtonsListener);
        mMaskHaroldinho.setOnClickListener(mButtonsListener);
        mMaskMiniTLC.setOnClickListener(mButtonsListener);
        mSavePicture.setOnClickListener(mCameraButtonsListener);
        mCancel.setOnClickListener(mCameraButtonsListener);

        setupUI();
    }

    /**
     * Setup UI with the path received from bundle
     */
    private void setupUI() {

        Intent callerIntent = getIntent();
        if(callerIntent != null) {

            Bundle bundle = callerIntent.getExtras();
            if(bundle != null) {
                mPath = bundle.getString(AppConstants.EXTRA_PICTURE_PATH, "");

                mCameraPreview.setBackground(Drawable.createFromPath(mPath));
                mCameraPreview.setPicturePath(mPath.substring(0, mPath.lastIndexOf('/') + 1));
                mCameraPreview.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Handle the click in the buttons of the screen to take picture or save the image
     */
    private View.OnClickListener mCameraButtonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()) {

                case R.id.camera_save:
                    int stringId;

                    if(mCameraPreview.savePicture()) {
                        setResult(AppConstants.RESPONSE_FROM_EDITOR);
                        stringId = R.string.save_photo_success;
                        EditPictureActivity.this.finish();
                    } else {
                        stringId = R.string.save_photo_error;
                    }

                    Toast.makeText(EditPictureActivity.this, stringId, Toast.LENGTH_LONG).show();
                    break;

                case R.id.camera_cancel:
                    if(mPath != null) {
                        mCameraPreview.setBackground(Drawable.createFromPath(mPath));
                    }
                    break;
            }
        }
    };

    /**
     * Handle the click in the buttons of the screen to add the necessary mask
     */
    private View.OnClickListener mButtonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int drawableId = -1;

            switch(view.getId()) {

                case R.id.camera_add_alegria:
                    drawableId = R.drawable.mask_text;
                    break;

                case R.id.camera_add_haroldinho:
                    drawableId = R.drawable.mask_haroldinho;
                    break;

                case R.id.camera_add_minitlc:
                    drawableId = R.drawable.mask_minitlc;
                    break;
            }

            if(drawableId > -1) {
                Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), drawableId);
                mCameraPreview.addMask(bitmap);
            }
        }
    };
}
