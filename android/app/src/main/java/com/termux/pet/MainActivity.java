package com.termux.pet;

import android.os.Bundle;
import android.view.WindowManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Request overlay permission if not granted
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }

        // Keep on top
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                             WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                             WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                             WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        // Transparent background
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Re-apply flags when focus changes
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                                 WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                                 WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                                 WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        }
    }
}
