package com.scanner.scanner;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.FileObserver;
import android.os.IBinder;
import android.util.Log;

public class ScanService extends Service {

    private static final String PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/";
    private static final String TAG = "ScanService";
    public static FileObserver mObserver;

    public ScanService() {
        initObserver();
    }

    private void initObserver() {
        mObserver = new FileObserver(PATH, FileObserver.CREATE) {
            @Override
            public void onEvent(int event, final String file) {
                Log.d(TAG, "File created " + PATH + file);
            }
        };
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startObserving();
    }

    @Override
    public void onDestroy() {
        mObserver.stopWatching();
        mObserver = null;
        super.onDestroy();
    }

    private void startObserving() {
        Log.d(TAG, PATH);
        mObserver.startWatching();
    }
}