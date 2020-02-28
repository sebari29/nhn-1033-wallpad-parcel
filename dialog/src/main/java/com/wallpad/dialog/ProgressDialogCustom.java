package com.wallpad.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;


/**
 * Created by Duong.
 */
public class ProgressDialogCustom extends ProgressDialog {

    Context context;
    private Dialog mProgressDialog;
    RotateAnimation rotateAnimation;
    View view;
    public ProgressDialogCustom(Context context){
        super(context);
        this.context=context;
        LayoutInflater factory = LayoutInflater.from(context);
        mProgressDialog = new Dialog(context);
        mProgressDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
         view = factory.inflate(R.layout.dialog_loading, null, false);
        mProgressDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(view);
        mProgressDialog.setCancelable(false);
        rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(4000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        new Handler().post(() -> view.findViewById(R.id.progressbar).startAnimation(rotateAnimation));
    }
   public void showDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
    }

    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }





}
