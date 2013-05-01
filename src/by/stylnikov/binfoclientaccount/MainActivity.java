package by.stylnikov.binfoclientaccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity implements TabListener {

	private static final String LOG_TAG = "BinfoClient";

	private CompanyFragment companyFragment;
	private PrivateFragment privateFragment;
	private PasswordFragment passwordFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d(LOG_TAG, "onCreate");

		companyFragment = new CompanyFragment();
		privateFragment = new PrivateFragment();
		passwordFragment = new PasswordFragment();

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab tab = actionBar.newTab();
		tab.setText(R.string.title_tab_company);
		tab.setTag(companyFragment);
		tab.setTabListener(this);
		actionBar.addTab(tab);

		tab = actionBar.newTab();
		tab.setText(R.string.title_tab_private);
		tab.setTag(privateFragment);
		tab.setTabListener(this);
		actionBar.addTab(tab);

		tab = actionBar.newTab();
		tab.setText(R.string.title_tab_password);
		tab.setTag(passwordFragment);
		tab.setTabListener(this);
		actionBar.addTab(tab);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Log.d(LOG_TAG, "Selected tab: " + tab.getText());
		if (tab.getTag() != null) {
			ft.replace(R.id.main_container, (Fragment) tab.getTag());
		} else{
			Log.d(LOG_TAG, "NULL");
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Log.d(LOG_TAG, "Unselected tab: " + tab.getText());
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Log.d(LOG_TAG, "Reselected tab: " + tab.getText());
	}
}
