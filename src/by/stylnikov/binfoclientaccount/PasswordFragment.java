package by.stylnikov.binfoclientaccount;

import android.app.Activity;
import android.os.Bundle;
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
	
	private Button saveButton;
	private EditText newPassEditText, repeatPassEditText;
	private String newPass, repeatNewPass;
	private Activity self;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate CompanyFragment");
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
        Log.d(LOG_TAG, "View CompanyFragment created");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "CompanyFragment onResume");
    }
    
    private void initVars(){
    	self = this.getActivity();
    	
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
    				newPassEditText.setError(self.getText(R.string.password_error));
    				repeatPassEditText.setError(self.getText(R.string.password_error));
    			}
    		} else {
    			result = false;
    			repeatPassEditText.setError(self.getText(R.string.incomplete_error));
    		}
    	} else{
    		result = false;
    		newPassEditText.setError(self.getText(R.string.incomplete_error));
    	}
    	return result;
    }
    
    protected void saveNewPassword() {
    	Log.d(LOG_TAG, "Send new password!");
		// TODO Send new pass via HTTP
	}
}
