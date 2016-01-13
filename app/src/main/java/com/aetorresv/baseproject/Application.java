package com.aetorresv.baseproject;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import lombok.Getter;
import lombok.Setter;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Application extends MultiDexApplication {

    public static Context appContext = null;
    @Getter
    private static Bus eventBus;

    @Getter
    @Setter
    public static int currentNavItem = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
        setTimber();
        initCalligraphy();

    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(getString(R.string.font_opensans_regular))
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    private void init() {
        appContext = this.getApplicationContext();
        eventBus = new Bus(ThreadEnforcer.ANY);
        //apiConfig = new ApiConfig(Properties.URL_BASE);
    }

    private void setTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private static class CrashReportingTree extends Timber.HollowTree {
        @Override
        public void i(String message, Object... args) {
            // TODO e.g., Crashlytics.log(String.format(message, args));
        }

        @Override
        public void i(Throwable t, String message, Object... args) {
            i(message, args); // Just add to the log.
        }

        @Override
        public void e(String message, Object... args) {
            i("BD_RESET_ARTICLES_ERROR: " + message, args); // Just add to the log.
        }

        @Override
        public void e(Throwable t, String message, Object... args) {
            e(message, args);

            // TODO e.g., Crashlytics.logException(t);
        }
    }

}
