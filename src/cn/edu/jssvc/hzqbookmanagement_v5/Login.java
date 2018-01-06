package cn.edu.jssvc.hzqbookmanagement_v5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	EditText username;
	EditText password;
	TextView register;
	Button login;
	private ImageView head_imageview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		findViews();
	}

	private void findViews() {
		username = (EditText) findViewById(R.id.username_edit);
		password = (EditText) findViewById(R.id.passwd_edit);
		login = (Button) findViewById(R.id.btn_login);
		register = (TextView) findViewById(R.id.newuser_text);
		head_imageview = (ImageView) findViewById(R.id.head_imageview);

		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = username.getText().toString();
				String pass = password.getText().toString();
				UserService uService = new UserService(Login.this);
				boolean flag = uService.login(name, pass);
				if (flag) {
					Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT)
							.show();
					Intent intent = new Intent(Login.this, Main.class);
					startActivity(intent);

					// 判断用户名是否为空
				} else if (name.equals("")) {
					DialogDemo.builder(Login.this, "错误信息", "用户名不能为空！");
					// 判断密码是否为空
				} else if (pass.equals("")) {
					DialogDemo.builder(Login.this, "错误信息", "密码不能为空！");
				} // 判断用户名和密码是否正确
				else if (!(name.equals(username) && pass.equals(password))) {
					DialogDemo.builder(Login.this, "错误信息", "用户名或密码错误，请重新输入");
				} else {
					Toast.makeText(Login.this, "请注册后再登陆", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Login.this, Register.class);
				startActivity(intent);
			}
		});
		head_imageview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this, Head.class);
				startActivity(intent);
			}
		});
	}

	// ============================================退出============================================

	// 因为Activity继承了TabActivity,所以不能监听到onKeyDown()方法，重写以下方法
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getRepeatCount() == 0) {
			// exitBy2Click(); //调用双击退出函数
			exit();
		} else {
			return super.dispatchKeyEvent(event);
		}
		return false;
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			isExit = false;
		}
		
	};
	private boolean isExit;

	public void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(Login.this, "再按一次退出我的应用", Toast.LENGTH_SHORT).show();
			handler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
		}
	}
}
