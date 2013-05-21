package by.stylnikov.binfoclientaccount;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CompanyFragment extends Fragment{
	private static final String LOG_TAG = "BinfoClient";
	private static final String PREF_NAME = "name";
	private static final String PREF_ADDRESS = "address";
	private static final String PREF_POST = "post";
	private static final String PREF_PHONE = "phone";
	private static final String PREF_FAX = "fax";
	private static final String PREF_SITE = "site";
	private static final String PREF_KEYS = "keys";
	private static final String PREF_SERVICES = "services";
	private static final String PREF_SYNC_COMPANY = "syncCompany";
	
	private SharedPreferences preferences;
	private Button saveButton;
	private EditText nameEditText, addressEditText, postEditText, phoneEditText;
	private EditText faxEditText, siteEditText, keysEditText, servicesEditText;
	private String name, address, post, phone, fax, site, keys, services;
	private boolean fieldscomplete;
	private Activity activity;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate CompanyFragment");
        initVars();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.company_layout, null);
        nameEditText = (EditText) v.findViewById(R.id.editTextCompanyName);
        addressEditText = (EditText) v.findViewById(R.id.editTextCompanyAddress);
        postEditText = (EditText) v.findViewById(R.id.editTextCompanyPost);
        phoneEditText = (EditText) v.findViewById(R.id.editTextCompanyPhone);
        faxEditText = (EditText) v.findViewById(R.id.editTextCompanyFax);
        siteEditText = (EditText) v.findViewById(R.id.editTextCompanySite);
        keysEditText = (EditText) v.findViewById(R.id.editTextCompanyKeys);
        servicesEditText = (EditText) v.findViewById(R.id.editTextCompanyServices);
        saveButton = (Button) v.findViewById(R.id.buttonSaveCompany);
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				readEditTexts();
				if(fieldscomplete){
					saveChanges();
				}
			}
		});
        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "View CompanyFragment created");
        nameEditText.setText(preferences.getString(PREF_NAME, ""));
        addressEditText.setText(preferences.getString(PREF_ADDRESS, ""));
        postEditText.setText(preferences.getString(PREF_POST, ""));
        phoneEditText.setText(preferences.getString(PREF_PHONE, ""));
        faxEditText.setText(preferences.getString(PREF_FAX, ""));
        siteEditText.setText(preferences.getString(PREF_SITE, ""));
        keysEditText.setText(preferences.getString(PREF_KEYS, ""));
        servicesEditText.setText(preferences.getString(PREF_SERVICES, ""));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "CompanyFragment onResume");
    }
    
    private void initVars() {
		activity = this.getActivity();
		preferences = PreferenceManager.getDefaultSharedPreferences(activity);
	}
    
    protected void readEditTexts() {
    	fieldscomplete = true;
    	name = readRequiredField(nameEditText);
    	address = readRequiredField(addressEditText);
    	post = readRequiredField(postEditText);
    	phone = readRequiredField(phoneEditText);
    	fax = faxEditText.getText().toString();
    	site = siteEditText.getText().toString();
    	keys = keysEditText.getText().toString();
    	services = servicesEditText.getText().toString();
	}
	
	protected String readRequiredField(EditText editText){
		String string = editText.getText().toString();
		if(string.length() < 1){
			editText.setError(activity.getText(R.string.incomplete_error));
			fieldscomplete = false;
		}
		return string;
	}
	
	protected void saveChanges() {
		Log.d(LOG_TAG, "Send changes!");
		SharedPreferences.Editor editor = preferences.edit();
    	editor.putString(PREF_NAME, name);
    	editor.putString(PREF_ADDRESS, address);
    	editor.putString(PREF_POST, post);
    	editor.putString(PREF_PHONE, phone);
    	editor.putString(PREF_FAX, fax);
    	editor.putString(PREF_SITE, site);
    	editor.putString(PREF_KEYS, keys);
    	editor.putString(PREF_SERVICES, services);
    	editor.putBoolean(PREF_SYNC_COMPANY, false);
    	editor.commit();
		Toast.makeText(activity, R.string.save_message_local, Toast.LENGTH_SHORT).show();
    	
		// TODO Send changes via HTTP
    	
//    	editor.putBoolean(PREF_SYNC_COMPANY, true);
//    	editor.commit();
//		Toast.makeText(activity, R.string.save_message_global, Toast.LENGTH_SHORT).show();
	}
}
