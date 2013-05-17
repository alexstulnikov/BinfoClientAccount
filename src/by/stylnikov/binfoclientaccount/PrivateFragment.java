package by.stylnikov.binfoclientaccount;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragment;

public class PrivateFragment extends SherlockFragment {
	private static final String LOG_TAG = "BinfoClient";

	private Button saveButton;
	private EditText firstNameEditText, fatherNameEditText, lastNameEditText,
			postEditText, emailEditText, aEmailEditText, phoneEditText;
	private String firstName, fatherName, lastName, post, email, aEmail, phone;
	private boolean fieldscomplete = true;
	private Activity self;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG, "onCreate CompanyFragment");
		initVars();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.private_layout, null);
		firstNameEditText = (EditText) v.findViewById(R.id.editTextPersonalFirstName);
		fatherNameEditText = (EditText) v.findViewById(R.id.editTextPersonalFatherName);
		lastNameEditText = (EditText) v.findViewById(R.id.editTextPersonalLastName);
		postEditText = (EditText) v.findViewById(R.id.editTextPersonalPost);
		emailEditText = (EditText) v.findViewById(R.id.editTextPersonalEmail);
		aEmailEditText = (EditText) v.findViewById(R.id.editTextPersonalAemail);
		phoneEditText = (EditText) v.findViewById(R.id.editTextPersonalPhone);
		saveButton = (Button) v.findViewById(R.id.buttonSavePersonal);
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
    	firstName = readRequiredField(firstNameEditText);
    	fatherName = readRequiredField(fatherNameEditText);
    	lastName = readRequiredField(lastNameEditText);
    	post = readRequiredField(postEditText);
    	email = readRequiredField(emailEditText);
    	aEmail = aEmailEditText.getText().toString();
    	phone = phoneEditText.getText().toString();
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
