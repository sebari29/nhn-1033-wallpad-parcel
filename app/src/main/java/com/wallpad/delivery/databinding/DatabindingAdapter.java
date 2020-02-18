package com.wallpad.delivery.databinding;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wallpad.delivery.R;
import com.wallpad.delivery.view.customview.loadmore.GsmartRecyclerview;
import com.wallpad.delivery.view.customview.loadmore.ILoadmore;

public class DatabindingAdapter {

    @BindingAdapter("android:visibility")
    public static void setVisibility(View target, boolean isVisible) {
        target.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("android:disable")
    public static void setDisable(View target, boolean disable) {
        target.setVisibility(disable ? View.GONE : View.VISIBLE);
    }

    @BindingAdapter("android:alpha")
    public static void setAlpha(View target, boolean isVisible) {
        target.setAlpha(isVisible ? 1f : 0.5f);
    }

    @BindingAdapter("android:elevation")
    public static void setElevation(CardView target, boolean isVisible) {
        target.setCardElevation(isVisible ? 0f : 2f);
    }

    @BindingAdapter("android:selected")
    public static void setSelected(View target, boolean selected) {
        target.setSelected(selected);
    }
//    @BindingAdapter("app:goneUnless")
//    public static void goneUnless(View view, Boolean visible) {
//        view.setVisibility( visible ? View.VISIBLE : View.INVISIBLE);
//    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, String imageUri) {
        if (imageUri == null) {
            view.setImageURI(null);
        } else {
            view.setImageURI(Uri.parse(imageUri));
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, Uri imageUri) {
        view.setImageURI(imageUri);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("android:adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("android:isLoad")
    public static void isLoad(GsmartRecyclerview recyclerView, Boolean isLoad) {
        recyclerView.setLoading(isLoad);
    }

    @BindingAdapter("android:hasNoMore")
    public static void hasNoMore(GsmartRecyclerview recyclerView, Boolean hasNoMore) {
        recyclerView.setNoMore(hasNoMore);
    }

    @BindingAdapter("android:setLoadmoreListener")
    public static void setLoadmoreListener(GsmartRecyclerview recyclerView, ILoadmore loadmore) {
        recyclerView.setLoadMore(loadmore);
    }

    @BindingAdapter("android:setEventType")
    public static void setEventType(TextView tv, String eventString) {
        tv.setText(eventString.equals("0") ? R.string.delivery_event_sent : R.string.delivery_event_received);
    }
}
