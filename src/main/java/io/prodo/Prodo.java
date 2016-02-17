package io.prodo;

import android.app.Application;
import android.preference.PreferenceManager;

public class Prodo extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		PreferenceManager.setDefaultValues(this, R.xml.preference_settings, false);
	}
}
