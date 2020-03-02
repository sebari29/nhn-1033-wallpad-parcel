package com.wallpad.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.wallpad.dialog.databinding.ConfirmDialogBinding;
import com.wallpad.dialog.dialoginterface.OnClickConfirmDialog;

public class ConfirmDialog extends Dialog {

    public final ObservableField<String> txtTileDialog = new ObservableField<>();
    public final ObservableField<String> txtContentDialog = new ObservableField<>();
    public final ObservableField<String> txtButtonCancelDialog = new ObservableField<>();
    public final ObservableField<String> txtButtonOkDialog = new ObservableField<>();
    public final ObservableField<Drawable> drawableDialog = new ObservableField<>();
    private final OnClickConfirmDialog onClickDialog;

    public ConfirmDialog(@NonNull Context context,
                         OnClickConfirmDialog onClickDialog) {
        super(context);
        this.onClickDialog = onClickDialog;
        clearBackgroundDialog();
        txtTileDialog.set(context.getResources().getString(R.string.txt_title_dialog_confirm));
        txtContentDialog.set(context.getResources().getString(R.string.txt_content_dialog_confirm));
        txtButtonCancelDialog.set(context.getResources().getString(R.string.txt_cancel_dialog_confirm));
        txtButtonOkDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableDialog.set(context.getResources().getDrawable(R.drawable.image_check));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public ConfirmDialog(@NonNull Context context,
                         OnClickConfirmDialog onClickDialog,
                         String title) {
        super(context);
        this.onClickDialog = onClickDialog;
        clearBackgroundDialog();
        txtTileDialog.set(title);
        txtContentDialog.set(context.getResources().getString(R.string.txt_content_dialog_confirm));
        txtButtonCancelDialog.set(context.getResources().getString(R.string.txt_cancel_dialog_confirm));
        txtButtonOkDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableDialog.set(context.getResources().getDrawable(R.drawable.image_check));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public ConfirmDialog(@NonNull Context context,
                         OnClickConfirmDialog onClickDialog,
                         String title,
                         String content) {
        super(context);
        this.onClickDialog = onClickDialog;
        clearBackgroundDialog();
        txtTileDialog.set(title);
        txtContentDialog.set(content);
        txtButtonCancelDialog.set(context.getResources().getString(R.string.txt_cancel_dialog_confirm));
        txtButtonOkDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableDialog.set(context.getResources().getDrawable(R.drawable.image_check));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public ConfirmDialog(@NonNull Context context,
                         OnClickConfirmDialog onClickDialog,
                         String title,
                         String content,
                         String txtButtonCancel) {
        super(context);
        this.onClickDialog = onClickDialog;
        clearBackgroundDialog();
        txtTileDialog.set(title);
        txtContentDialog.set(content);
        txtButtonCancelDialog.set(txtButtonCancel);
        txtButtonOkDialog.set(context.getResources().getString(R.string.txt_ok_dialog_confirm));
        drawableDialog.set(context.getResources().getDrawable(R.drawable.image_check));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public ConfirmDialog(@NonNull Context context,
                         OnClickConfirmDialog onClickDialog,
                         String title, String content,
                         String txtButtonCancel,
                         String txtOk) {
        super(context);
        this.onClickDialog = onClickDialog;
        clearBackgroundDialog();
        txtTileDialog.set(title);
        txtContentDialog.set(content);
        txtButtonCancelDialog.set(txtButtonCancel);
        txtButtonOkDialog.set(txtOk);
        drawableDialog.set(context.getResources().getDrawable(R.drawable.image_check));
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public ConfirmDialog(@NonNull Context context,
                         OnClickConfirmDialog onClickDialog,
                         String title, String content,
                         String txtButtonCancel,
                         String txtOk, Drawable drawable) {
        super(context);
        this.onClickDialog = onClickDialog;
        clearBackgroundDialog();
        txtTileDialog.set(title);
        txtContentDialog.set(content);
        txtButtonCancelDialog.set(txtButtonCancel);
        txtButtonOkDialog.set(txtOk);
        drawableDialog.set(drawable);
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    private void setUpDialog() {
        ConfirmDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.confirm_dialog, null, false);
        binding.setView(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.getRoot());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        if (getWindow() != null) {
            lp.copyFrom(getWindow().getAttributes());

        }
        lp.width = (int) getContext().getResources().getDimension(R.dimen._420dp);
        lp.height = (int) getContext().getResources().getDimension(R.dimen._400dp);
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);
    }

    public ConfirmDialog(@NonNull Context context, int themeResId, OnClickConfirmDialog onClickDialog) {
        super(context, themeResId);
        this.onClickDialog = onClickDialog;
        clearBackgroundDialog();
        this.setCanceledOnTouchOutside(false);
        setUpDialog();
    }

    public void onClickApply() {
        if (onClickDialog != null) {
            onClickDialog.onClick(true);
        }
        dismiss();
    }

    public void onClickCancel() {
        if (onClickDialog != null) {
            onClickDialog.onClick(false);
        }
        dismiss();
    }

    @Override
    public void show() {
        handleHideNavigationWithDialog();
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
            this.setOnShowListener(dialog -> ConfirmDialog.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE));
        }
    }

    private void clearBackgroundDialog() {
        Window window = getWindow();
        if (window != null)
            window.setBackgroundDrawableResource(android.R.color.transparent);
    }
}
