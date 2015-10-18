package com.example.myapplication_kakao.com.example.myapplication_kakao.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Created by 상유 on 2015-10-18.
 */
public class ParcelableParseObject extends ParseObject implements Parcelable,Serializable {
    private static final long serialVersionUID = 1L;
    protected ParcelableParseObject(Parcel in) {

    }

    public static final Creator<ParcelableParseObject> CREATOR = new Creator<ParcelableParseObject>() {
        @Override
        public ParcelableParseObject createFromParcel(Parcel in) {
            return new ParcelableParseObject(in);
        }

        @Override
        public ParcelableParseObject[] newArray(int size) {
            return new ParcelableParseObject[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

}
