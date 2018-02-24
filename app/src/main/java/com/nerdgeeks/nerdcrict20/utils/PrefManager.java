package com.nerdgeeks.nerdcrict20.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.nerdgeeks.nerdcrict20.R;

/**
 * Created by hp on 2/6/2017.
 */

public class PrefManager {
    private static final String TAG = PrefManager.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private Context mContext;

    private String PREF_NAME;
    private String MAP_ARRAY="Ads_Check";


    public PrefManager(Context mContext) {
        this.mContext = mContext;
        PREF_NAME=mContext.getString(R.string.app_name);
        int PRIVATE_MODE = 0;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }
    public void storeAdsCheck(int count){
        Editor editor = sharedPreferences.edit();
        editor.putString(MAP_ARRAY,String.valueOf(count));
        editor.apply();
        Log.e(TAG,"Inserted");
    }

    public int AdsCheck(){
       int Ads_check;
        if(sharedPreferences.contains(MAP_ARRAY)){
            Ads_check = Integer.valueOf(sharedPreferences.getString(MAP_ARRAY,null));
            return Ads_check;
        } else {
            return 1;
        }
    }

}
