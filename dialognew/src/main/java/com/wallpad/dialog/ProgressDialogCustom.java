package com.wallpad.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Duong.
 */
public class ProgressDialogCustom extends ProgressDialog {

    Context context;
    private Dialog mProgressDialog;
    private Timer timeOut;
    private final static int TIME_OUT = 3000;

    public ProgressDialogCustom(Context context) {
        super(context);
        this.context = context;
        LayoutInflater factory = LayoutInflater.from(context);
        mProgressDialog = new Dialog(context);
        mProgressDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = factory.inflate(R.layout.dialog_loading, null, false);
        mProgressDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(view);
        mProgressDialog.setCancelable(false);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        view.findViewById(R.id.progressbar).startAnimation(rotateAnimation);
        timeOut = new Timer(false);
    }

    public void showDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
            try {
                timeOut.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        hideDialog();
                        cancel();
                    }
                }, TIME_OUT);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            try {
                cancel();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
