package org.telegram.techrunch;

import android.content.Context;
import android.content.SharedPreferences;

import org.telegram.messenger.techranch.R;

/**
 * Created by matelskyvv on 5/20/16.
 */
public class TechrunchConfig {

    private static final String SELECTED_CITY_KEY = "selected city";
    private static final String USE_NEAR_ME_KEY = "use near me";

    private final Context mContext;
    private final SharedPreferences mPreferences;

    public TechrunchConfig(Context context) {
        mContext = context;
        mPreferences = context.getSharedPreferences("techrunch_churches", Context.MODE_PRIVATE);
    }

    public String getSelectedCity() {
        return mPreferences.getString(SELECTED_CITY_KEY, mContext.getString(R.string.techrunch_no_city));
    }

    public void setSelectedCity(String city) {
        mPreferences.edit().putString(SELECTED_CITY_KEY, city).apply();
    }

    public boolean isCitySelected() {
        return mPreferences.contains(SELECTED_CITY_KEY);
    }

    public void setUseNearMe(boolean value) {
        mPreferences.edit().putBoolean(USE_NEAR_ME_KEY, value).apply();
    }

    public boolean isUseNearMeEnabled() {
        return mPreferences.getBoolean(USE_NEAR_ME_KEY, false);
    }

}
