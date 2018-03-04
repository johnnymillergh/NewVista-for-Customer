package com.jm.newvista.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.jm.newvista.R;

/**
 * Created by Johnny on 3/4/2018.
 */

public class AppUtil {

    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return context.getString(R.string.about_version) + " " + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
