package by.stylnikov.binfoclientaccount;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity{
	
	private static final String PREF_LOGIN = "login";
	private static final String PREF_PASS = "password";
	
	private EditText loginEditText, passwordEditText;
	private String login, password;
	private Button loginButton;
	private Activity activity;
	
	private SharedPreferences preferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		activity = this;
		
		loginEditText = (EditText) findViewById(R.id.editTextLogin);
		passwordEditText = (EditText) findViewById(R.id.editTextPassword);
		loginButton = (Button) findViewById(R.id.buttonLogin);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				login = loginEditText.getText().toString();
				password = passwordEditText.getText().toString();
				// TODO send login-password to server and check response
				if(true){
					savePreferences();
					Intent intent = new Intent(activity, MainActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

	protected void savePreferences() {
		preferences = PreferenceManager.getDefaultSharedPreferences(activity);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(PREF_LOGIN, login);
		editor.putString(PREF_PASS, password);
		editor.commit();
	}
}
