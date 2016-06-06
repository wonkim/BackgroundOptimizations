package com.wonkim.backgroundoptimizations;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.NetworkRequest.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 This sample code shows 3 ways of getting network connectivity change status through

 1. BroadcastReceiver
 2. JobScheduler
 3. ConnectivityManager

 Please see https://developer.android.com/preview/features/background-optimization.html
 for more detail
*/

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = "MainActivity";
    private static final int MY_BACKGROUND_JOB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // onClick listener for broadcast recv
    public void registerBroadcastRecv(View view) {
        getApplicationContext().registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Log.d(LOG_TAG, "onReceive");
                Util.logNetworkInfo(LOG_TAG, context);
            }
        }, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    // onClick listener for schedule job button
    public void scheduleJob(View view) {
        final Context context = getApplicationContext();
        JobScheduler js = (JobScheduler)context.getSystemService(
                Context.JOB_SCHEDULER_SERVICE);
        JobInfo job = new JobInfo.Builder(
                MY_BACKGROUND_JOB,
                new ComponentName(context, MyJobService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(true)
                .build();
        js.schedule(job);
    }

    // onClick listener for register network cb button
    public void registerNetworkCB(View view) {
        final Context context = getApplicationContext();
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest request = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();
        cm.registerNetworkCallback(request, new ConnectivityManager.NetworkCallback() {
            public void onAvailable (Network network) {
                Log.d(LOG_TAG, "onAvailable");
            }
        });
    }
}