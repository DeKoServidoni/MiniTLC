package com.dekoservidoni.minitlc.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.dekoservidoni.minitlc.R;
import com.dekoservidoni.minitlc.utils.AppConstants;
import com.dekoservidoni.minitlc.utils.CameraSurfaceView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class responsible to show a camera and build the picture of the user
 *
 * Created by DeKoServidoni on 2/26/16.
 */
public class EditPictureActivity extends AppCompatActivity  {

    /** UI Components */
    @Bind(R.id.camera_preview) CameraSurfaceView mCameraPreview;
    @Bind(R.id.camera_add_alegria) FloatingActionButton mMaskBallon;
    @Bind(R.id.camera_add_haroldinho) FloatingActionButton mMaskHaroldinho;
    @Bind(R.id.camera_add_minitlc) FloatingActionButton mMaskMiniTLC;
    @Bind(R.id.camera_save) FloatingActionButton mSavePicture;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_edit_picture);
        ButterKnife.bind(this);

        mMaskBallon.setOnClickListener(mButtonsListener);
        mMaskHaroldinho.setOnClickListener(mButtonsListener);
        mMaskMiniTLC.setOnClickListener(mButtonsListener);
        mSavePicture.setOnClickListener(mCameraButtonsListener);

        setupUI();
    }

    /**
     * Setup UI with the path received from bundle
     */
    private void setupUI() {

        Intent callerIntent = getIntent();
        if (callerIntent != null) {

            Bundle bundle = callerIntent.getExtras();
            if (bundle != null) {
                String path = bundle.getString(AppConstants.EXTRA_PICTURE_PATH, "");

                if (!TextUtils.isEmpty(path)) {
                    mCameraPreview.setImageDrawable(Drawable.createFromPath(path));
                    mCameraPreview.setPicturePath(path.substring(0, path.lastIndexOf('/') + 1));
                    mCameraPreview.requestLayout();
                }
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
                        stringId = R.string.save_photo_success;
                        EditPictureActivity.this.finish();
                    } else {
                        stringId = R.string.save_photo_error;
                    }

                    Toast.makeText(EditPictureActivity.this, stringId, Toast.LENGTH_LONG).show();
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
