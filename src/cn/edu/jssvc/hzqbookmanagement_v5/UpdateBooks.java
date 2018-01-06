package cn.edu.jssvc.hzqbookmanagement_v5;

import cn.edu.jssvc.hzqbookmanagement_v5.DatabaseHelper;
import cn.edu.jssvc.hzqbookmanagement_v5.DialogDemo;
import cn.edu.jssvc.hzqbookmanagement_v5.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateBooks extends Activity {

	private EditText updatebook_edit;
	private EditText updateauthor_edit;
	private EditText updateprice_edit;
	private Button btn_update;
	private TextView setting_about_back;
	private Button btn_abandon;
	private DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.updatebooks);

		updatebook_edit = (EditText) findViewById(R.id.updatebook_edit);
		updateauthor_edit = (EditText) findViewById(R.id.updateauthor_edit);
		updateprice_edit = (EditText) findViewById(R.id.updateprice_edit);
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_abandon = (Button) findViewById(R.id.btn_abandon);
		setting_about_back = (TextView) findViewById(R.id.setting_about_back);

		dbHelper = new DatabaseHelper(this);

		btn_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String upb = updatebook_edit.getText().toString();
				String upa = updateauthor_edit.getText().toString();
				String upp = updateprice_edit.getText().toString();
				SQLiteDatabase db = dbHelper.getWritableDatabase();

				if (upb.equals("")) {
					DialogDemo.builder(UpdateBooks.this, "错误信息", "书名不能为空！");
				} else if (upa.equals("")) {
					DialogDemo.builder(UpdateBooks.this, "错误信息", "作者不能为空！");
				} else if (upp.equals("")) {
					DialogDemo.builder(UpdateBooks.this, "错误信息", "价格不能为空！");
				}

				else {
					String sql_up = "select * from bookstore where bookname=? and author=?";
					Cursor cursor = db.rawQuery(sql_up,
							new String[] { upb, upa });
					if (cursor.moveToFirst() == true) {
						cursor.close();
						ContentValues values = new ContentValues();
						values.put("price", upp);
						db.update("bookstore", values,
								"bookname=? and author=?", new String[] { upb,
										upa });
						Toast.makeText(UpdateBooks.this, "更新成功",
								Toast.LENGTH_SHORT).show();
					} else {
						DialogDemo.builder(UpdateBooks.this, "错误信息",
								"书名作者不一致，请重新输入");
					}
				}
			}

		});
		btn_abandon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				updatebook_edit.setText("");
				updateauthor_edit.setText("");
				updateprice_edit.setText("");
			}
		});
		setting_about_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}

		});
	}
}
