package com.wallpad.net.socket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DuongKK on 6/20/2017.
 */

public class SocketResponse implements Parcelable {

    protected SocketResponse(Parcel in) {
    }

    public static final Creator<SocketResponse> CREATOR = new Creator<SocketResponse>() {
        @Override
        public SocketResponse createFromParcel(Parcel in) {
            return new SocketResponse(in);
        }

        @Override
        public SocketResponse[] newArray(int size) {
            return new SocketResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
