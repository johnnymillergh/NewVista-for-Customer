package com.jm.newvista.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageUtil {
    private static final String PACKAGE_NAME = "com.jm.newvista";
    private static final Context context = ApplicationUtil.context;
    public static final String ERROR_LABEL = "";
    private static final String DEFAULT_COUNTRY = "US";
    private static final String DEFAULT_LANGUAGE = "en";

    public static String getStringByLocale(int stringId, String language, String country) {
        Resources resources = getApplicationResource(context.getApplicationContext().getPackageManager(),
                PACKAGE_NAME, new Locale(language, country));
        if (resources == null) {
            return ERROR_LABEL;
        } else {
            try {
                return resources.getString(stringId);
            } catch (Exception e) {
                return ERROR_LABEL;
            }
        }
    }

    public static String[] getStringArrayByLocale(int stringId, String language, String country) {
        Resources resources = getApplicationResource(context.getApplicationContext().getPackageManager(),
                PACKAGE_NAME, new Locale(language, country));
        if (resources == null) {
            return null;
        } else {
            try {
                return resources.getStringArray(stringId);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static String getStringToEnglish(int stringId) {
        return getStringByLocale(stringId, DEFAULT_LANGUAGE, DEFAULT_COUNTRY);
    }

    public static String[] getStringArrayToEnglish(int stringId) {
        return getStringArrayByLocale(stringId, DEFAULT_LANGUAGE, DEFAULT_COUNTRY);
    }

    private static Resources getApplicationResource(PackageManager pm, String pkgName, Locale l) {
        Resources resourceForApplication = null;
        try {
            resourceForApplication = pm.getResourcesForApplication(pkgName);
            updateResource(resourceForApplication, l);
        } catch (PackageManager.NameNotFoundException e) {

        }
        return resourceForApplication;
    }

    private static void updateResource(Resources resource, Locale l) {
        Configuration config = resource.getConfiguration();
        config.locale = l;
        resource.updateConfiguration(config, null);
    }
}
