package by.stylnikov.binfoclientaccount;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

public class NetworkReceiver extends BroadcastReceiver {
	
	private static final String LOG_TAG = "BinfoClient";
	private static final String PREF_SYNC_COMPANY = "syncCompany";
	private static final String PREF_SYNC_PRIVATE = "syncPrivate";

	private SharedPreferences preferences;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conn.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			Log.d(LOG_TAG, "Do something: Network connected!");
			
			if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				Log.d(LOG_TAG, "Do something: WiFi connected!");
			} else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				Log.d(LOG_TAG, "Do something: Mobile connected!");
			}
			
			preferences = PreferenceManager.getDefaultSharedPreferences(context);
			if(!preferences.getBoolean(PREF_SYNC_COMPANY, true)){
				 Log.d(LOG_TAG, "Company Data unsynced - try to send data");
				//TODO try to send data
			}
			if(!preferences.getBoolean(PREF_SYNC_PRIVATE, true)){
				Log.d(LOG_TAG, "Private Data unsynced - try to send data");
				//TODO try to send data
			}
		} else {
			Log.d(LOG_TAG, "Do something: Not connected!");
		}
	}
}
