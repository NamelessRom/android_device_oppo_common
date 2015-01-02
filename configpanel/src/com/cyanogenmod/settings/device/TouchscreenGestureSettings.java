package com.cyanogenmod.settings.device;

import com.cyanogenmod.settings.device.utils.NodePreferenceActivity;

import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.Preference;
import android.preference.SwitchPreference;

public class TouchscreenGestureSettings extends NodePreferenceActivity {
    private static final String KEY_HAPTIC_FEEDBACK = "touchscreen_haptic_feedback";

    private static final String PROP_HAPTIC_FEEDBACK = "persist.gestures.haptic";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.touchscreen_panel);

        final SwitchPreference hapticFeedback =
                (SwitchPreference) findPreference(KEY_HAPTIC_FEEDBACK);
        hapticFeedback.setChecked(SystemProperties.getBoolean(PROP_HAPTIC_FEEDBACK, false));
        hapticFeedback.setOnPreferenceChangeListener(this);
    }

    @Override public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        if (KEY_HAPTIC_FEEDBACK.equals(key)) {
            final boolean value = (Boolean) newValue;
            SystemProperties.set(PROP_HAPTIC_FEEDBACK, value ? "true" : "false");
            return true;
        }

        return super.onPreferenceChange(preference, newValue);
    }
}
