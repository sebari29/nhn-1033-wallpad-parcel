package com.wallpad.delivery.view.customview.loadmore;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;

public class GsmartRecyclerview extends RecyclerView {
    private int totalItemCount;
    private int lastVisibleItem;
    private boolean isLoading;
    ILoadmore loadMore;
    private int visibleThreshold = 6;
    private boolean isNoMore = false;

    private void init() {

    }

    public GsmartRecyclerview(@NonNull Context context) {
        super(context);
        init();
    }

    public GsmartRecyclerview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public GsmartRecyclerview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

    }

    //
    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        try {
            if (getLayoutManager() instanceof WrapContentLinearLayoutManager) {
                totalItemCount = getLayoutManager().getItemCount();
                lastVisibleItem = ((WrapContentLinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                if (!isNoMore && !isLoading && lastVisibleItem == totalItemCount - 1) {
                    if (loadMore != null) {
                        loadMore.onLoadmore();
                    }
                    isLoading = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getLastVisibleItem() {
        return lastVisibleItem;
    }

    public void setLastVisibleItem(int lastVisibleItem) {
        this.lastVisibleItem = lastVisibleItem;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        LogUtils.d("loading more  ... "+loading);
        isLoading = loading;
    }


    public ILoadmore getLoadMore() {
        return loadMore;
    }

    public void setLoadMore(ILoadmore loadMore) {
        this.loadMore = loadMore;
    }

    public boolean isNoMore() {
        return isNoMore;
    }

    public void setNoMore(boolean noMore) {
        LogUtils.d("---------------No more item now.---------------" + noMore);
        isNoMore = noMore;
    }

    public int getVisibleThreshold() {
        return visibleThreshold;
    }

    public void setVisibleThreshold(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }
}
