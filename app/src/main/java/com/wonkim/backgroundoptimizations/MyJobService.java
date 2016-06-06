package com.wonkim.backgroundoptimizations;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by wonil on 6/6/16.
 */
public class MyJobService extends JobService {
    private final String LOG_TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(LOG_TAG, "onStartJob");
        Util.logNetworkInfo(LOG_TAG, getApplicationContext());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
