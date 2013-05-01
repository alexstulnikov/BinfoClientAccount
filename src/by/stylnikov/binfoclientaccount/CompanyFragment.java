package by.stylnikov.binfoclientaccount;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompanyFragment extends Fragment{
	private static final String LOG_TAG = "BinfoClient";
	
	public String qwe = "QWE";
	
	private Activity self;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate CompanyFragment");
        self = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.company_layout, null);
        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "View CompanyFragment created");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "CompanyFragment onResume");
    }
}
