package com.test.android.testapp.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by basi on 23/11/16.
 */

@Data
@Accessors(prefix = "m")
public class AppInfo implements Comparable<Object>{
    long mLastUpdateTime;
    String mName;
    String mIcon;

    public AppInfo(String name, String icon, long lastUpdateTime) {
        mName = name;
        mIcon = icon;
        mLastUpdateTime = lastUpdateTime;
    }


    @Override
    public int compareTo(Object o) {
        AppInfo f= (AppInfo) o;
        return getName().compareTo(f.getName());
    }
}
