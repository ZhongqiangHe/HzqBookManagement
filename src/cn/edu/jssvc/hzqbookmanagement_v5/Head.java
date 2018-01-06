package cn.edu.jssvc.hzqbookmanagement_v5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Head extends Activity {

	private TextView setting_about_back;
	private String[] data = { "姓名：何忠强", "性别：男", "年龄：20", "专业：计算机应用技术2（嵌入）",
			"学校：苏州市职业大学", "联系方式：18913504162" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.head);
		setting_about_back = (TextView) findViewById(R.id.setting_about_back);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Head.this,
				android.R.layout.simple_list_item_1, data);
		ListView listView = (ListView) findViewById(R.id.listview_head);
		listView.setAdapter(adapter);
		setting_about_back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
	}
}
