package com.wallpad.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.wallpad.dialog.common.Constant;

/**
 *
 */
public class AlertDialog extends Dialog {

    public final ObservableField<String> txtTitleAlertDialog = new ObservableField<>();
    public final ObservableField<String> txtContentAlertDialog = new ObservableField<>();
    public final ObservableField<Drawable> drawableAlertDialog = new ObservableField<>();
    public final ObservableField<String> txtOkAlertDialog = new ObservableField<>();
    public final ObservableField<String> dataTime = new ObservableField<>();
    public final ObservableField<String> dataDetails = new ObservableField<>();
    public final ObservableField<String> dialogTypeAlertDialog = new ObservableField<>();
    IClickDialog iClickDialog;

    public AlertDialog(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Light_NoActionBar_FullScreen);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(context.getResources().getString(R.string.txt_title_alert_dialog));
        txtContentAlertDialog.set(context.getResources().getString(R.string.txt_content_alert_dialog));
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(context.getResources().getDrawable(R.drawable.image_popup_emergency));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public AlertDialog(@NonNull Context context, IClickDialog iClickDialog) {
        super(context, R.style.Theme_AppCompat_Light_NoActionBar_FullScreen);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(context.getResources().getString(R.string.txt_title_alert_dialog));
        txtContentAlertDialog.set(context.getResources().getString(R.string.txt_content_alert_dialog));
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(context.getResources().getDrawable(R.drawable.image_popup_emergency));
        this.setCanceledOnTouchOutside(false);
        this.iClickDialog = iClickDialog;
        setUpDialog();
    }

    public AlertDialog(@NonNull Context context, String title) {
        super(context, R.style.Theme_AppCompat_Light_NoActionBar_FullScreen);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(context.getResources().getString(R.string.txt_content_alert_dialog));
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(context.getResources().getDrawable(R.drawable.image_popup_emergency));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public AlertDialog(@NonNull Context context, String title, String content) {
        super(context, R.style.Theme_AppCompat_Light_NoActionBar_FullScreen);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(content);
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(context.getResources().getDrawable(R.drawable.image_popup_emergency));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public AlertDialog(@NonNull Context context, String title, String content, Drawable drawable) {
        super(context, R.style.Theme_AppCompat_Light_NoActionBar_FullScreen);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(content);
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(drawable);
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public void setValueDialog(String title, String content, Drawable drawable, String dialogType, String time, String details) {
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(content);
        txtOkAlertDialog.set(getContext().getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(drawable);
        dataTime.set(time);
        dataDetails.set(details);
        dialogTypeAlertDialog.set(dialogType);
    }

    public AlertDialog(@NonNull Context context, String title, String content, Drawable drawable, String txtOk) {
        super(context, R.style.Theme_AppCompat_Light_NoActionBar_FullScreen);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(content);
        txtOkAlertDialog.set(txtOk);
        drawableAlertDialog.set(drawable);
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(context.getResources().getString(R.string.txt_title_alert_dialog));
        txtContentAlertDialog.set(context.getResources().getString(R.string.txt_content_alert_dialog));
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(context.getResources().getDrawable(R.drawable.image_popup_emergency));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public AlertDialog(@NonNull Context context, int themeResId, String title) {
        super(context, themeResId);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(context.getResources().getString(R.string.txt_content_alert_dialog));
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(context.getResources().getDrawable(R.drawable.image_popup_emergency));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();

    }

    public AlertDialog(@NonNull Context context, int themeResId, String title, String content) {
        super(context, themeResId);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(content);
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(context.getResources().getDrawable(R.drawable.image_popup_emergency));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public AlertDialog(@NonNull Context context, int themeResId, String title, String content, Drawable drawable) {
        super(context, themeResId);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(content);
        txtOkAlertDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableAlertDialog.set(drawable);
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public AlertDialog(@NonNull Context context, int themeResId, String title, String content, Drawable drawable, String txtOk) {
        super(context, themeResId);
        clearBackgroundDialog();
        txtTitleAlertDialog.set(title);
        txtContentAlertDialog.set(content);
        txtOkAlertDialog.set(txtOk);
        drawableAlertDialog.set(drawable);
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public IClickDialog getiClickDialog() {
        return iClickDialog;
    }

    public void setiClickDialog(IClickDialog iClickDialog) {
        this.iClickDialog = iClickDialog;
    }

    @Override
    public void show() {
        handleHideNavigationWithDialog();
//        if (!isShowing())
        super.show();
    }

    private void handleHideNavigationWithDialog() {
        Window window = getWindow();
        if (window != null) {
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            //    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
            this.setOnShowListener(dialog -> AlertDialog.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE));
        }
    }

    private void clearBackgroundDialog() {
        Window window = getWindow();
        if (window != null)
            window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    private void setUpDialog() {
        AlertDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.alert_dialog, null, false);
        binding.setView(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.getRoot());
    }

    /**
     * Start application based on pop up type
     *
     * @param popupType Pop up type
     */
    public void onClickItem(String popupType) {
        String packageName = null;
        switch (popupType) {
            case Constant.TYPE_ALERT_POPUP:
                //Add package name
                packageName = "";
                break;
            case Constant.TYPE_PARKING_POPUP:
                packageName = Constant.PACKAGE_PARKING_LOT;
                break;
            case Constant.TYPE_PARCEL_POPUP:
                packageName = Constant.PACKAGE_DELEVERY;
                break;
            case Constant.TYPE_NOTI_POPUP:
                packageName = Constant.PACKAGE_NOTICE;
        }
        assert packageName != null;
        if (iClickDialog != null) {
            iClickDialog.onClick(packageName);
            iClickDialog.onReadNotice(dataTime.get(), dataDetails.get());
        }
        dismiss();
    }


    public interface IClickDialog {
        void onClick(String packageName);

        void onReadNotice(String time, String details);
    }
}
