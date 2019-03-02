package com.npdevelopment.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class PortalObject implements Parcelable {

    private String mUrl;
    private String mTitle;

    public PortalObject(String mUrl, String mTitle) {
        this.mUrl = mUrl;
        this.mTitle = mTitle;
    }

    public PortalObject(Parcel parcel) {
        this.mUrl = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Initialize the object
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mTitle);
    }

    // Create a new instance of the Parcelable class, instantiating it from the given Parcel
    // whose data had previously been written by Parcelable.writeToParcel()
    public static final Creator<PortalObject> CREATOR = new Creator<PortalObject>() {
        @Override
        public PortalObject createFromParcel(Parcel parcel) {
            return new PortalObject(parcel);
        }

        @Override
        public PortalObject[] newArray(int i) {
            return new PortalObject[i];
        }
    };
}
