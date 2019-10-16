package com.example.mysettingpreferance;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

public class MyPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String DEFAULT_VALUE = "tidak ada";
    private String NAME, EMAIL, PHONE, AGE, LOVE;

    private EditTextPreference namePreference, emailPreference, phonePreference, agePreference;
    private CheckBoxPreference  isLovePreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferance);

        init();
        setSummarys();

    }

    private void setSummarys() {
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();
        namePreference.setSummary(sh.getString(NAME, DEFAULT_VALUE));
        emailPreference.setSummary(sh.getString(EMAIL, DEFAULT_VALUE));
        phonePreference.setSummary(sh.getString(PHONE, DEFAULT_VALUE));
        agePreference.setSummary(sh.getString(AGE, DEFAULT_VALUE));
        isLovePreference.setChecked(sh.getBoolean(LOVE, false));

    }

    private void init() {
        NAME = getResources().getString(R.string.key_name);
        EMAIL = getResources().getString(R.string.key_email);
        PHONE = getResources().getString(R.string.key_phone);
        AGE = getResources().getString(R.string.key_age);
        LOVE = getResources().getString(R.string.key_love);

        namePreference = (EditTextPreference) findPreference(NAME);
        emailPreference = (EditTextPreference) findPreference(EMAIL);
        phonePreference = (EditTextPreference) findPreference(PHONE);
        agePreference = (EditTextPreference) findPreference(AGE);
        isLovePreference = (CheckBoxPreference) findPreference(LOVE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(NAME)){
            namePreference.setSummary(sharedPreferences.getString(NAME, DEFAULT_VALUE));
        }
        if (s.equals(EMAIL)) {
            emailPreference.setSummary(sharedPreferences.getString(EMAIL, DEFAULT_VALUE));
        }
        if (s.equals(PHONE)) {
            phonePreference.setSummary(sharedPreferences.getString(PHONE, DEFAULT_VALUE));
        }
        if (s.equals(AGE)) {
            agePreference.setSummary(sharedPreferences.getString(AGE, DEFAULT_VALUE));
        }
        if (s.equals(LOVE)) {
            isLovePreference.setChecked(sharedPreferences.getBoolean(LOVE, false));
        }
    }
}
