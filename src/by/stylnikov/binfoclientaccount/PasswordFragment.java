package by.stylnikov.binfoclientaccount;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragment;

public class PasswordFragment extends SherlockFragment{
	private static final String LOG_TAG = "BinfoClient";
	private static final String PREF_PASS = "pass";
	private static final String PREF_SYNC_PASS = "syncPass";
	
	private SharedPreferences preferences;
	private Button saveButton;
	private EditText newPassEditText, repeatPassEditText;
	private String newPass, repeatNewPass;
	private Activity activity;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate PasswordFragment");
        initVars();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.password_layout, null);
        newPassEditText = (EditText) v.findViewById(R.id.editTextNewPass);
        repeatPassEditText = (EditText) v.findViewById(R.id.editTextRepeateNewPass);
        saveButton = (Button) v.findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				readEditTexts();
				if(checkIfComplete()){
					saveNewPassword();
				}
			}
		});
        return v;
    }

	public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "View PasswordFragment created");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "PasswordFragment onResume");
    }
    
    private void initVars(){
    	activity = this.getActivity();
    	preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }
    
    protected void readEditTexts() {
    	newPass = newPassEditText.getText().toString();
    	repeatNewPass = repeatPassEditText.getText().toString();
	}
    
    private boolean checkIfComplete(){
    	boolean result;
    	if(newPass.length() > 0){
    		if(repeatNewPass.length() > 0){
    			if(newPass.equals(repeatNewPass)){
    				result = true;
    			} else{
    				result = false;
    				newPassEditText.setError(activity.getText(R.string.password_error));
    				repeatPassEditText.setError(activity.getText(R.string.password_error));
    			}
    		} else {
    			result = false;
    			repeatPassEditText.setError(activity.getText(R.string.incomplete_error));
    		}
    	} else{
    		result = false;
    		newPassEditText.setError(activity.getText(R.string.incomplete_error));
    	}
    	return result;
    }
    
    protected void saveNewPassword() {
    	Log.d(LOG_TAG, "Send new password!");
    	SharedPreferences.Editor editor = preferences.edit();
    	editor.putString(PREF_PASS, newPass);
    	editor.putBoolean(PREF_SYNC_PASS, false);
    	editor.commit();
    	
		// TODO Send new pass via HTTP
    	
//    	editor.putBoolean(PREF_SYNC_PASS, true);
//    	editor.commit();
	}
}
