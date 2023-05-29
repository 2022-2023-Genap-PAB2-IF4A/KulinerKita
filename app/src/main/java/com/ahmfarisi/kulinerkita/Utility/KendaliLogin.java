package com.ahmfarisi.kulinerkita.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class KendaliLogin {
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    private Context ctx;

    public String keySP_username = "ya03xVHzi2NWGaapYda5AA_username";
    public String keySP_nama_lengkap = "ya03xVHzi2NWGaapYda5AA_nama_lengkap";
    public String keySP_email = "ya03xVHzi2NWGaapYda5AA_password";

    public KendaliLogin(Context ctx) {
        this.ctx = ctx;
    }

    public void setPref(String key, String value){
        sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        spEditor = sp.edit();
        spEditor.putString(key, value);
        spEditor.commit();
    }

    public String getPref(String key){
        sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sp.getString(key, null);
    }

    public Boolean isLogin(String key){
        if(getPref(key) != null){
            return true;
        }
        else{
            return false;
        }
    }
}
