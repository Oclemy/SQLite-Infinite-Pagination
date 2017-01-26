package com.tutorials.hp.sqliteinfinitepager;

import android.app.Application;

import co.uk.rushorm.android.AndroidInitializeConfig;
import co.uk.rushorm.core.RushCore;

/**
 * Created by Oclemy on 1/25/2017 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //INITIALIZE RUSHCORE CONFIGURATION
        AndroidInitializeConfig config=new AndroidInitializeConfig(getApplicationContext());
        RushCore.initialize(config);
    }
}
