package com.wallpad.delivery.data.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "t_delivery")
public class Delivery extends BaseObservable implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "time_send")
    private String timeSend;

    @ColumnInfo(name = "time_receive")
    private String timeReceive;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "payment")
    private String payment;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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
        dest.writeString(this.payment);
    }

    public Delivery() {
    }

    protected Delivery(Parcel in) {
        this.id = in.readString();
        this.timeSend = in.readString();
        this.timeReceive = in.readString();
        this.name = in.readString();
        this.payment = in.readString();
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
