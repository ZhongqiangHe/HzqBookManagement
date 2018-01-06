package cn.edu.jssvc.hzqbookmanagement_v5;

import cn.edu.jssvc.hzqbookmanagement_v5.DatabaseHelper;
import cn.edu.jssvc.hzqbookmanagement_v5.DialogDemo;
import cn.edu.jssvc.hzqbookmanagement_v5.QueryBooksListView;
import cn.edu.jssvc.hzqbookmanagement_v5.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QueryBooks extends Activity implements OnClickListener {

	private EditText edit_querybook;
	private TextView setting_about_back;
	private DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.querybooks);
		Button btn_search = (Button) findViewById(R.id.btn_search);
		setting_about_back = (TextView) findViewById(R.id.setting_about_back);
		dbHelper = new DatabaseHelper(this);
		btn_search.setOnClickListener(this);
		setting_about_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_search:
			edit_querybook = (EditText) findViewById(R.id.querybook_edit);
			String querybook = edit_querybook.getText().toString();

			SQLiteDatabase sdb = dbHelper.getReadableDatabase();
			String sql = "select * from bookstore where bookname=?";
			Cursor cursor = sdb.rawQuery(sql, new String[] { querybook });

			if (querybook.equals("")) {
				DialogDemo.builder(QueryBooks.this, "错误信息", "书名不能为空！");
			} else if (cursor.moveToFirst() == true) {
				cursor.close();
				String data = querybook;
				Intent intent = new Intent(QueryBooks.this, QueryBooksListView.class);
				intent.putExtra("extra_data", data);
				startActivity(intent);
			} else {
				DialogDemo.builder(QueryBooks.this, "错误信息", "您要查询的图书不存在！");
			}
			break;
		case R.id.setting_about_back:
			finish();
			break;
		default:
			break;
		}
	}

}