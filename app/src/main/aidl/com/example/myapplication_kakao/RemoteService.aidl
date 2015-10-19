// RemoteService.aidl
package com.example.myapplication_kakao;

// Declare any non-default types here with import statements
import com.example.myapplication_kakao.RemoteServiceCallBack;
interface RemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void registerCallBack(RemoteServiceCallBack rsc);
    void unregisterCallBack(RemoteServiceCallBack rsc);
    void addString(String str);
}
