package by.stylnikov.binfoclientaccount;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CompanyFragment extends Fragment{
	private static final String LOG_TAG = "BinfoClient";
	
	private Button saveButton;
	private EditText nameEditText, addressEditText, postEditText, phoneEditText;
	private EditText faxEditText, siteEditText, keysEditText, servicesEditText;
	private String name, address, post, phone, fax, site, keys, services;
	private boolean fieldscomplete;
	private Activity self;
	
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
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "CompanyFragment onResume");
    }
    
    private void initVars() {
		self = this.getActivity();
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
			editText.setError(self.getText(R.string.incomplete_error));
			fieldscomplete = false;
		}
		return string;
	}
	
	protected void saveChanges() {
		Log.d(LOG_TAG, "Send changes!");
		// TODO Send changes via HTTP
	}
}
