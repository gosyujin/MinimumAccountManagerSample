package io.github.gosyujin.minimumaccountmanagersample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by ataka on 2016/03/01.
 */
public class AuthenticationService extends Service {
    private Authenticator mAuchenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("AuthenticationService#onCreate");
        mAuchenticator = new Authenticator(this);
    }

    @Override
    public void onDestroy() {
        System.out.println("AuthenticationService#onDelete");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("AuthenticationService#onBind");
        return mAuchenticator.getIBinder();
    }
}