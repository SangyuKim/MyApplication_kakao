// RemoteServiceCallBack.aidl
package com.example.myapplication_kakao;

// Declare any non-default types here with import statements

oneway interface RemoteServiceCallBack {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void callback(String msg);
}
