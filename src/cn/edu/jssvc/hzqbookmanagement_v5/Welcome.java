package cn.edu.jssvc.hzqbookmanagement_v5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import cn.edu.jssvc.hzqbookmanagement_v5.R;
import cn.edu.jssvc.hzqbookmanagement_v5.Welcome;

public class Welcome extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		Start();
	}

	public void Start() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent();
				intent.setClass(Welcome.this, Login.class);
				startActivity(intent);
				finish();
			}
		}.start();
	}
}
