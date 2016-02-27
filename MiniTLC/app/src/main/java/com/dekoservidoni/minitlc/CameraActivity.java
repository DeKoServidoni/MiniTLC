package com.dekoservidoni.minitlc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.dekoservidoni.minitlc.customs.CameraSurfaceView;
import com.dekoservidoni.minitlc.managers.MiniCameraManager;
import com.dekoservidoni.minitlc.utils.MiniLog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class responsible to show a camera and build the picture of the user
 *
 * Created by DeKoServidoni on 2/26/16.
 */
@SuppressWarnings("deprecation")
public class CameraActivity extends AppCompatActivity implements MiniCameraManager.MiniCameraCallback {

    /** UI Components */
    @Bind(R.id.camera_container) FrameLayout mCameraContainer;
    @Bind(R.id.camera_preview) CameraSurfaceView mCameraPreview;

    @Bind(R.id.camera_add_alegria) FloatingActionButton mMaskBallon;
    @Bind(R.id.camera_add_haroldinho) FloatingActionButton mMaskHaroldinho;
    @Bind(R.id.camera_add_minitlc) FloatingActionButton mMaskMiniTLC;
    @Bind(R.id.camera_cancel) FloatingActionButton mCancel;
    @Bind(R.id.camera_take_picture) FloatingActionButton mTakePicture;
    @Bind(R.id.camera_save) FloatingActionButton mSavePicture;

    /** Camera manager instance */
    private MiniCameraManager mCameraManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);

        mMaskBallon.setOnClickListener(mButtonsListener);
        mMaskHaroldinho.setOnClickListener(mButtonsListener);
        mMaskMiniTLC.setOnClickListener(mButtonsListener);

        mTakePicture.setOnClickListener(mCameraButtonsListener);
        mSavePicture.setOnClickListener(mCameraButtonsListener);
        mCancel.setOnClickListener(mCameraButtonsListener);

        mCameraManager = new MiniCameraManager(this, mCameraContainer, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraManager.startCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraManager.release();
    }

    @Override
    public void onTakePictureFinished(String picturePath) {
        mCameraManager.stopCamera();

        MiniLog.e("Picture path: " + picturePath);

        mCameraPreview.setBackground(Drawable.createFromPath(picturePath));
        mCameraPreview.setPicturePath(picturePath.substring(0, picturePath.lastIndexOf('/') + 1));
        mCameraPreview.setVisibility(View.VISIBLE);
        showEditUI();
    }

    /**
     * Handle the click in the buttons of the screen to take picture or save the image
     */
    private View.OnClickListener mCameraButtonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch(view.getId()) {

                case R.id.camera_take_picture:
                    mCameraManager.takePicture();
                    break;

                case R.id.camera_save:
                    boolean result = mCameraPreview.savePicture();
                    if(result) {
                        CameraActivity.this.finish();
                    }
                    break;

                case R.id.camera_cancel:
                    showPhotoUI();
                    mCameraManager.startCamera();
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

    /**
     * Show the editors UI
     */
    private void showEditUI() {
        mMaskBallon.setVisibility(View.VISIBLE);
        mMaskHaroldinho.setVisibility(View.VISIBLE);
        mMaskMiniTLC.setVisibility(View.VISIBLE);
        mSavePicture.setVisibility(View.VISIBLE);
        mCancel.setVisibility(View.VISIBLE);

        mCameraContainer.setVisibility(View.GONE);
        mTakePicture.setVisibility(View.GONE);
    }

    /**
     * Show the photo taken UI
     */
    private void showPhotoUI() {
        mMaskBallon.setVisibility(View.GONE);
        mMaskHaroldinho.setVisibility(View.GONE);
        mMaskMiniTLC.setVisibility(View.GONE);
        mSavePicture.setVisibility(View.GONE);
        mCancel.setVisibility(View.GONE);
        mCameraPreview.setVisibility(View.GONE);

        mCameraContainer.setVisibility(View.VISIBLE);
        mTakePicture.setVisibility(View.VISIBLE);
    }
}
