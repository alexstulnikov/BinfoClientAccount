package by.stylnikov.binfoclientaccount;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class PrivateFragment extends SherlockFragment {
	private static final String LOG_TAG = "BinfoClient";
	private static final String PREF_FIRST_NAME = "firstName";
	private static final String PREF_FATHER_NAME = "fatherName";
	private static final String PREF_LAST_NAME = "lastName";
	private static final String PREF_POST = "post";
	private static final String PREF_EMAIL = "email";
	private static final String PREF_AEMAIL = "aEmail";
	private static final String PREF_PHONE = "phone";
	private static final String PREF_SYNC_PRIVATE = "syncPrivate";

	private SharedPreferences preferences;
	private Button saveButton;
	private EditText firstNameEditText, fatherNameEditText, lastNameEditText,
			postEditText, emailEditText, aEmailEditText, phoneEditText;
	private String firstName, fatherName, lastName, post, email, aEmail, phone;
	private boolean fieldscomplete = true;
	private Activity activity;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG, "onCreate PrivateFragment");
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
		Log.d(LOG_TAG, "View PrivateFragment created");
		firstNameEditText.setText(preferences.getString(PREF_FIRST_NAME, ""));
		fatherNameEditText.setText(preferences.getString(PREF_FATHER_NAME, ""));
		lastNameEditText.setText(preferences.getString(PREF_LAST_NAME, ""));
		postEditText.setText(preferences.getString(PREF_POST, ""));
		emailEditText.setText(preferences.getString(PREF_EMAIL, ""));
		aEmailEditText.setText(preferences.getString(PREF_AEMAIL, ""));
		phoneEditText.setText(preferences.getString(PREF_PHONE, ""));
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(LOG_TAG, "PrivateFragment onResume");
	}
	
	private void initVars() {
		activity = this.getActivity();
		preferences = PreferenceManager.getDefaultSharedPreferences(activity);
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
			editText.setError(activity.getText(R.string.incomplete_error));
			fieldscomplete = false;
		}
		return string;
	}
	
	protected void saveChanges() {
		Log.d(LOG_TAG, "Send changes!");
		SharedPreferences.Editor editor = preferences.edit();
    	editor.putString(PREF_FIRST_NAME, firstName);
    	editor.putString(PREF_FATHER_NAME, fatherName);
    	editor.putString(PREF_LAST_NAME, lastName);
    	editor.putString(PREF_POST, post);
    	editor.putString(PREF_EMAIL, email);
    	editor.putString(PREF_AEMAIL, aEmail);
    	editor.putString(PREF_PHONE, phone);
    	editor.putBoolean(PREF_SYNC_PRIVATE, false);
    	editor.commit();
    	Toast.makeText(activity, R.string.save_message_local, Toast.LENGTH_SHORT).show();
    	
		// TODO Send new pass via HTTP
    	
//    	editor.putBoolean(PREF_SYNC_PRIVATE, true);
//    	editor.commit();
//    	Toast.makeText(activity, R.string.save_message_global, Toast.LENGTH_SHORT).show();
	}
}
