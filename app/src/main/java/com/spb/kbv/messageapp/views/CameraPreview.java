package com.spb.kbv.messageapp.views;

import android.hardware.Camera;
import android.view.SurfaceView;

import com.spb.kbv.messageapp.activities.BaseActivity;

public class CameraPreview extends SurfaceView{
    public CameraPreview (BaseActivity activity){
        super(activity);
    }

    public void setCamera(Camera camera, Camera.CameraInfo cameraInfo){}

}
