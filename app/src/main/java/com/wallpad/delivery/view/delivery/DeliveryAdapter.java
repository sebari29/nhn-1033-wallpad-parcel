package com.wallpad.delivery.view.delivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.delivery.R;
import com.wallpad.delivery.common.Constant;
import com.wallpad.delivery.data.model.Delivery;
import com.wallpad.delivery.databinding.DeliveryEmptyItemBinding;
import com.wallpad.delivery.databinding.DeliveryItemBinding;
import com.wallpad.delivery.databinding.LoadmoreItemBinding;
import com.wallpad.delivery.view.customview.loadmore.LoadmoreViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.wallpad.delivery.view.customview.loadmore.ILoadmore.TYPE_LOAD_MORE;

public class DeliveryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_ITEM = 1;
    private final static int TYPE_EMPTY_ITEM = 0;
    private final static String TAG = DeliveryAdapter.class.getSimpleName();

    private List<Delivery> mNotifyList = new ArrayList<>();


    public DeliveryAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            LayoutInflater layoutInflater =
                    LayoutInflater.from(parent.getContext());
            DeliveryItemBinding itemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.delivery_item, parent, false);
            itemBinding.setAdapter(this);
            return new DeliveryViewHolder(itemBinding);
        } else if (viewType == TYPE_LOAD_MORE) {
            LayoutInflater layoutInflater =
                    LayoutInflater.from(parent.getContext());
            LoadmoreItemBinding itemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.loadmore_item, parent, false);
            return new LoadmoreViewHolder(itemBinding);
        } else {
            LayoutInflater layoutInflater =
                    LayoutInflater.from(parent.getContext());
            DeliveryEmptyItemBinding itemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.delivery_empty_item, parent, false);
            return new EmptyViewHolder(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            Delivery item = mNotifyList.get(position);
            ((DeliveryViewHolder) holder).bind(item);
        }
    }

    @Override
    public int getItemCount() {
        if (mNotifyList.size() < 5) {
            return Constant.SIZE_ITEM_IN_LIST;
        }
        if (mNotifyList.size() > Constant.MAX_SIZE_LIST) {
            return Constant.MAX_SIZE_LIST;
        }
        return mNotifyList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position < mNotifyList.size()) {
            if (mNotifyList.get(position) == null)
                return TYPE_LOAD_MORE;
            return TYPE_ITEM;

        } else {
            return TYPE_EMPTY_ITEM;

        }
    }

    public void setNoticeList(List<Delivery> noticeList) {
        if (noticeList == null) return;
        mNotifyList.clear();
        mNotifyList.addAll(noticeList);
        notifyDataSetChanged();
    }

    public void handleItemClick(Delivery notice) {
        LogUtils.d(TAG, "handleItemClick() - " + notice.getId());
    }

    public void addLoadmoreView() {
        mNotifyList.add(null);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Delivery> o) {
        int oldPos = mNotifyList.size();
        mNotifyList.addAll(o);
//        notifyDataSetChanged();
        notifyItemRangeInserted(oldPos, mNotifyList.size());
    }

    public void removeLoadmore() {
        if (mNotifyList.size() > 0) {
            int indexLast = getItemCount() - 1;
            mNotifyList.remove(indexLast);
            notifyItemRemoved(indexLast);
            LogUtils.d("remove loadmoreview now ...");
//            notifyItemRangeRemoved(indexLast, getItemCount());
//            notifyDataSetChanged();
        }
    }

    public List<Delivery> getmNotifyList() {
        return mNotifyList;
    }

    public void remove(int i) {
        mNotifyList.remove(i);
    }

    public void addItem(List<Delivery> o) {
        mNotifyList.addAll(o);

    }

    class DeliveryViewHolder extends RecyclerView.ViewHolder {
        private final DeliveryItemBinding mBinding;

        DeliveryViewHolder(DeliveryItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Delivery notice) {
            mBinding.setItem(notice);
            mBinding.executePendingBindings();
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(DeliveryEmptyItemBinding binding) {
            super(binding.getRoot());
        }

    }

}
