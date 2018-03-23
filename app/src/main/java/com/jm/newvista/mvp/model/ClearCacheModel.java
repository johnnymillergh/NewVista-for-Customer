package com.jm.newvista.mvp.model;

import android.content.Context;

import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.CacheUtil;

/**
 * Created by Johnny on 3/23/2018.
 */

public class ClearCacheModel extends BaseModel {

    public String getCacheSize(Context context) {
        return CacheUtil.getTotalCacheSize(context);
    }

    public String getInternalMemorySize(Context context) {
        return CacheUtil.getInternalMemorySize(context);
    }

    public void deleteCache(Context context, DeleteCacheListener deleteCacheListener) {
        CacheUtil.clearAllCache(context);
        deleteCacheListener.onDeleteDone();
    }

    @Override
    public void cancel() {
    }

    public interface DeleteCacheListener {
        void onDeleteDone();
    }
}
