package com.cyanogenmod.settings.device.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Constants {

    // Preference keys
    public static final String TOUCHPAD_STATE_KEY = "touchpad_enable_state";
    public static final String TOUCHPAD_LONG_PRESS_STATE_KEY = "touchpad_long_click_state";
    public static final String TOUCHPAD_DOUBLE_CLICK_STATE_KEY = "touchpad_double_tap_state";
    public static final String TOUCHPAD_DOUBLETAP_KEY = "touchpad_enable_doubletap";
    public static final String TOUCHPAD_LONGPRESS_KEY = "touchpad_enable_longpress";
    public static final String OCLICK_CONNECT_KEY = "oclick_connect";
    public static final String OCLICK_DEVICE_ADDRESS_KEY = "oclick_device_address";
    public static final String OCLICK_SNAPSHOT_KEY = "oclick_take_snapshot";
    public static final String OCLICK_FIND_PHONE_KEY = "oclick_find_my_phone";
    public static final String OCLICK_FENCE_KEY = "oclick_fence";
    public static final String OCLICK_DISCONNECT_ALERT_KEY = "oclick_disconnect_alert";
    public static final String TOUCHSCREEN_CAMERA_GESTURE_KEY = "touchscreen_gesture_camera";
    public static final String TOUCHSCREEN_DIALER_GESTURE_KEY = "touchscreen_gesture_dialer";
    public static final String TOUCHSCREEN_MUSIC_GESTURE_KEY = "touchscreen_gesture_music";
    public static final String TOUCHSCREEN_FLASHLIGHT_GESTURE_KEY =
            "touchscreen_gesture_flashlight";

    // Proc nodes
    public static final String TOUCH_PAD_NODE = "/proc/touchpad/enable";
    public static final String TOUCHSCREEN_CAMERA_NODE = "/proc/touchpanel/camera_enable";
    public static final String TOUCHSCREEN_DIALER_NODE = "/proc/touchpanel/dialer_enable";
    public static final String TOUCHSCREEN_MUSIC_NODE = "/proc/touchpanel/music_enable";
    public static final String TOUCHSCREEN_FLASHLIGHT_NODE = "/proc/touchpanel/flashlight_enable";

    // Proc nodes default values
    public static final boolean TOUCH_PAD_DEFAULT = false;
    public static final boolean TOUCHSCREEN_CAMERA_DEFAULT = true;
    public static final boolean TOUCHSCREEN_DIALER_DEFAULT = true;
    public static final boolean TOUCHSCREEN_MUSIC_DEFAULT = true;
    public static final boolean TOUCHSCREEN_FLASHLIGHT_DEFAULT = true;

    // Notification slider
    public static final String KEYCODE_SLIDER_TOP =
        "/sys/devices/soc.0/tri_state_key.82/keyCode_top";
    public static final String KEYCODE_SLIDER_MIDDLE =
        "/sys/devices/soc.0/tri_state_key.82/keyCode_middle";
    public static final String KEYCODE_SLIDER_BOTTOM =
        "/sys/devices/soc.0/tri_state_key.82/keyCode_bottom";

    // Holds <preference_key> -> <proc_node> mapping
    public static final Map<String, String> sNodePreferenceMap = new HashMap<String, String>();

    // Holds <preference_key> -> <default_values> mapping
    public static final Map<String, Boolean> sNodeDefaultMap = new HashMap<String, Boolean>();

    static {
        sNodePreferenceMap.put(TOUCHPAD_STATE_KEY, TOUCH_PAD_NODE);
        sNodePreferenceMap.put(TOUCHSCREEN_CAMERA_GESTURE_KEY, TOUCHSCREEN_CAMERA_NODE);
        sNodePreferenceMap.put(TOUCHSCREEN_DIALER_GESTURE_KEY, TOUCHSCREEN_DIALER_NODE);
        sNodePreferenceMap.put(TOUCHSCREEN_MUSIC_GESTURE_KEY, TOUCHSCREEN_MUSIC_NODE);
        sNodePreferenceMap.put(TOUCHSCREEN_FLASHLIGHT_GESTURE_KEY, TOUCHSCREEN_FLASHLIGHT_NODE);

        sNodeDefaultMap.put(TOUCHPAD_STATE_KEY, TOUCH_PAD_DEFAULT);
        sNodeDefaultMap.put(TOUCHSCREEN_CAMERA_GESTURE_KEY, TOUCHSCREEN_CAMERA_DEFAULT);
        sNodeDefaultMap.put(TOUCHSCREEN_DIALER_GESTURE_KEY, TOUCHSCREEN_DIALER_DEFAULT);
        sNodeDefaultMap.put(TOUCHSCREEN_MUSIC_GESTURE_KEY, TOUCHSCREEN_MUSIC_DEFAULT);
        sNodeDefaultMap.put(TOUCHSCREEN_FLASHLIGHT_GESTURE_KEY, TOUCHSCREEN_FLASHLIGHT_DEFAULT);
    }

    public static void savePreferenceInt(Context context, String key, int value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(key, value).apply();
    }

    public static boolean isPreferenceEnabled(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, defaultValue);
    }

    public static int getPreferenceInteger(Context context, String key, int defaultValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, defaultValue);
    }
}
