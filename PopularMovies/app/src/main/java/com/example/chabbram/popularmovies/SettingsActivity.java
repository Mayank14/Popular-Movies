package com.example.chabbram.popularmovies;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by chabbram on 12/7/2016.
 */

public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        bindPreferenceSummaryTovalue(findPreference(getString(R.string.pref_key)));
    }

    private void bindPreferenceSummaryTovalue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                                                          .getString(preference.getKey(),""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String value = newValue.toString();
        Log.v("SettingsActivity","The value is given by"+value);
        if(preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference)preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            Log.v("SettingsActivity","The index is given as:"+prefIndex);
            if(prefIndex>=0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
                Log.v("SettingsActivity","The summary set is:"+listPreference.getEntries()[prefIndex]);
            }
        }
        else {
            preference.setSummary(value);
        }
        return true;
    }
}
