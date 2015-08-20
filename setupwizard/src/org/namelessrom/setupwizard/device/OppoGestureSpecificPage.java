/*
 * <!--
 *    Copyright (C) 2015 The NamelessRom Project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * -->
 */

package org.namelessrom.setupwizard.device;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.CheckBox;

import com.cyanogenmod.setupwizard.setup.Page;
import com.cyanogenmod.setupwizard.setup.SetupDataCallbacks;
import com.cyanogenmod.setupwizard.setup.SetupPage;
import com.cyanogenmod.setupwizard.ui.SetupPageFragment;

import org.namelessrom.setupwizard.R;
import org.namelessrom.setupwizard.SetupWizardApp;

import cyanogenmod.hardware.CMHardwareManager;

public class OppoGestureSpecificPage extends SetupPage {
    public static final String TAG = "OppoGestureSpecificPage";

    public OppoGestureSpecificPage(Context context, SetupDataCallbacks callbacks) {
        super(context, callbacks);
    }

    @Override
    public Fragment getFragment(FragmentManager fragmentManager, int action) {
        Fragment fragment = fragmentManager.findFragmentByTag(getKey());
        if (fragment == null) {
            Bundle args = new Bundle();
            args.putString(Page.KEY_PAGE_ARGUMENT, getKey());
            args.putInt(Page.KEY_PAGE_ACTION, action);
            fragment = new OppoGestureSettingsFragment();
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public String getKey() {
        return TAG;
    }

    @Override
    public int getTitleResId() {
        return R.string.setup_oppo_gestures;
    }

    @Override
    public void onFinishSetup() { }

    public static class OppoGestureSettingsFragment extends SetupPageFragment {
        private static final String ACTION_CONTROL = "org.namelessrom.device.extra.ACTION_CONTROL";

        private static final String EXTRA_CONTROL = "control";
        private static final String EXTRA_VALUE = "value";

        private CMHardwareManager mCmHardwareManager;

        private CheckBox mDoubleTap;
        private CheckBox mCamera;
        private CheckBox mFlash;
        private CheckBox mMusic;
        private CheckBox mDialer;

        @Override
        protected void initializePage() {
            mCmHardwareManager = CMHardwareManager.getInstance(getActivity());

            View doubleTapView = mRootView.findViewById(R.id.oppo_gesture_dt2w);
            mDoubleTap = (CheckBox) mRootView.findViewById(R.id.oppo_gesture_dt2w_checkbox);
            if (mCmHardwareManager.isSupported(CMHardwareManager.FEATURE_TAP_TO_WAKE)) {
                mDoubleTap.setChecked(mCmHardwareManager.get(CMHardwareManager.FEATURE_TAP_TO_WAKE));
                doubleTapView.setOnClickListener(mDoubleTapClickListener);
            } else {
                doubleTapView.setEnabled(false);
            }

            View cameraView = mRootView.findViewById(R.id.oppo_gesture_camera);
            cameraView.setOnClickListener(mCameraClickListener);

            mCamera = (CheckBox) mRootView.findViewById(R.id.oppo_gesture_camera_checkbox);
            mCamera.setChecked(true);

            View flashView = mRootView.findViewById(R.id.oppo_gesture_flashlight);
            flashView.setOnClickListener(mFlashClickListener);

            mFlash = (CheckBox) mRootView.findViewById(R.id.oppo_gesture_flashlight_checkbox);
            mFlash.setChecked(true);

            View musicView = mRootView.findViewById(R.id.oppo_gesture_music);
            musicView.setOnClickListener(mMusicClickListener);

            mMusic = (CheckBox) mRootView.findViewById(R.id.oppo_gesture_music_checkbox);
            mMusic.setChecked(true);

            View dialerView = mRootView.findViewById(R.id.oppo_gesture_dialer);
            dialerView.setOnClickListener(mDialerClickListener);

            mDialer = (CheckBox) mRootView.findViewById(R.id.oppo_gesture_dialer_checkbox);
            mDialer.setChecked(true);
        }

        @Override
        protected int getLayoutResource() {
            return R.layout.setup_oppo_gestures;
        }

        private View.OnClickListener mDoubleTapClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = !mDoubleTap.isChecked();
                mDoubleTap.setChecked(checked);

                mCmHardwareManager.set(CMHardwareManager.FEATURE_TAP_TO_WAKE, checked);
            }
        };

        private View.OnClickListener mCameraClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = !mCamera.isChecked();
                mCamera.setChecked(checked);

                sendGestureBroadcast("touchscreen_gesture_camera", checked);
            }
        };

        private View.OnClickListener mFlashClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = !mFlash.isChecked();
                mFlash.setChecked(checked);

                sendGestureBroadcast("touchscreen_gesture_flashlight", checked);
            }
        };

        private View.OnClickListener mMusicClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = !mMusic.isChecked();
                mMusic.setChecked(checked);

                sendGestureBroadcast("touchscreen_gesture_music", checked);
            }
        };

        private View.OnClickListener mDialerClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = !mDialer.isChecked();
                mDialer.setChecked(checked);

                sendGestureBroadcast("touchscreen_gesture_dialer", checked);
            }
        };

        private void sendGestureBroadcast(String control, boolean checked) {
            final Intent intent = new Intent(ACTION_CONTROL);
            intent.putExtra(EXTRA_CONTROL, control);
            intent.putExtra(EXTRA_VALUE, checked ? "1" : "0");
            SetupWizardApp.get().sendBroadcastAsUser(intent, UserHandle.CURRENT);
        }

    }
}
