package com.lib.utils;

import android.app.Application;
import android.util.Log;

/**
 *
 */
public class AppContext {

    public static final Application INSTANCE;

    public static final int ENV_RELEASE = 0;
    public static final int ENV_BETA = 1;
    public static final int ENV_ALPHA = 2;

    static {
        Application app = null;
        try {
            app = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
            if (app == null)
                throw new IllegalStateException("Static initialization of Applications must be on main thread.");
        } catch (final Exception e) {
            Log.e("a", "Failed to get current application from AppGlobals." + e.getMessage());
            try {
                app = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            } catch (final Exception ex) {
                Log.e("a", "Failed to get current application from ActivityThread." + e.getMessage());
            }
        } finally {
            INSTANCE = app;
        }
    }
}