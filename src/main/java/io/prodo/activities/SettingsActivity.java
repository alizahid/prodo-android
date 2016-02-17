package io.prodo.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.prodo.R;

public class SettingsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == android.R.id.home) {
			finish();

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

		private static final String API_ENDPOINT = "api_endpoint";
		private static final String API_TOKEN = "api_token";

		private SharedPreferences preferences;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preference_settings);

			preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

			findPreference(API_ENDPOINT).setSummary(preferences.getString(API_ENDPOINT, null));
			findPreference(API_TOKEN).setSummary(preferences.getString(API_TOKEN, getString(R.string.no_token)));
		}

		@Override
		public void onResume() {
			super.onResume();

			preferences.registerOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onPause() {
			super.onPause();

			preferences.unregisterOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			Preference preference = findPreference(key);

			if (preference instanceof EditTextPreference) {
				EditTextPreference editTextPreference = (EditTextPreference) preference;

				preference.setSummary(editTextPreference.getText());
			}
		}
	}
}
