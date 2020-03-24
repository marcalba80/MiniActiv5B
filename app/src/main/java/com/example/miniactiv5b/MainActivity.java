package com.example.miniactiv5b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    boolean wifiConnected;
    boolean mobileConnected;

    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(br, filter);
        checkConnectivity();
    }

    public void web(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFrag, new WebFrag()).commit();
    }

    public void image(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFrag, new ImageFrag()).commit();
    }

    private void checkConnectivity() {
        new NetworkAsyncTask(this).execute();

    }

    private class NetworkAsyncTask extends AsyncTask<Void, Void, Void> {
        private Context mContext;

        public NetworkAsyncTask(Context context){
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ConnectivityManager connMgr =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                wifiConnected = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
                mobileConnected = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            } else {
                wifiConnected = false;
                mobileConnected = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            if (wifiConnected) {
                Toast.makeText(mContext, getString(R.string.wifi), Toast.LENGTH_SHORT).show();
            } else if (mobileConnected) {
                Toast.makeText(mContext, getString(R.string.mobile), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, getString(R.string.noNetwork), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkConnectivity();
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            checkConnectivity();
        }
    }
}
