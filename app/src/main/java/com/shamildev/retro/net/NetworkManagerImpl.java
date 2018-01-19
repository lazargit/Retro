package com.shamildev.retro.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.scope.ApplicationScope;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Schamil  on 15.11.2017.
 */


public class NetworkManagerImpl extends BroadcastReceiver implements NetworkManager {

    Context context;
    private IntentFilter connectivityIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

    private Map<String, NetworkListener> listeners = new HashMap<>();

    public NetworkManagerImpl() {

    }

    @Inject
    public NetworkManagerImpl(Context context) {
        this.context = context;
        NetworkManagerImpl networkManager = this;

        Log.d("NetworkManagerImpl", "+"+this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NetworkManagerImpl", " "+intent.getAction().trim());
        if (isNetworkAvailable()) {
            if (!isInitialStickyBroadcast()) {
                for (NetworkListener networkListener : listeners.values()) {
                    if (networkListener != null) {
                        networkListener.onNetworkAvailable();
                    }
                }
            }
        }else{
            for (NetworkListener networkListener : listeners.values()) {
                if (networkListener != null) {
                    networkListener.onNetworkAvailable();
                }
            }
        }

    }

    @Override
    public boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;

    }



    @Override
    public void start() {
        context.registerReceiver(this, connectivityIntentFilter);
    }

    @Override
    public void stop() {
        context.unregisterReceiver(this);
    }



    @Override
    public void add(String tag, NetworkListener networkListener) {
        listeners.put(tag, networkListener);
    }

    @Override
    public void remove(String tag) {
        listeners.remove(tag);
    }
}
