package com.wallpad.delivery.data.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

public class Delivery extends BaseObservable implements Parcelable {

    private String id;

    private String timeSend;

    private String timeReceive;

    private String name;
    private String status;

    public String getId() {
        return id;
    }

    public void setId( String id) {
        this.id = id;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public String getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(String timeReceive) {
        this.timeReceive = timeReceive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Delivery() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.timeSend);
        dest.writeString(this.timeReceive);
        dest.writeString(this.name);
        dest.writeString(this.status);
    }

    protected Delivery(Parcel in) {
        this.id = in.readString();
        this.timeSend = in.readString();
        this.timeReceive = in.readString();
        this.name = in.readString();
        this.status = in.readString();
    }

    public static final Creator<Delivery> CREATOR = new Creator<Delivery>() {
        @Override
        public Delivery createFromParcel(Parcel source) {
            return new Delivery(source);
        }

        @Override
        public Delivery[] newArray(int size) {
            return new Delivery[size];
        }
    };
}
