package cn.edu.jssvc.hzqbookmanagement_v5;

import java.util.ArrayList;
import java.util.List;
import cn.edu.jssvc.hzqbookmanagement_v5.AddBooks;
import cn.edu.jssvc.hzqbookmanagement_v5.DatabaseHelper;
import cn.edu.jssvc.hzqbookmanagement_v5.DeleteBooks;
import cn.edu.jssvc.hzqbookmanagement_v5.DialogDemo;
import cn.edu.jssvc.hzqbookmanagement_v5.QueryBooks;
import cn.edu.jssvc.hzqbookmanagement_v5.R;
import cn.edu.jssvc.hzqbookmanagement_v5.UpdateBooks;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Main extends Activity {

	private DatabaseHelper dbHelper;
	private TextView setting_about_back;
//	private String[] data = { "创建书库", "添加图书", "更新图书", "查询图书", "删除图书", "浏览图书" };

	private List<Icons> IconsList = new ArrayList<Icons>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		setting_about_back = (TextView) findViewById(R.id.setting_about_back);
		
		initIcon(); 
		IconsAdapter adapter = new IconsAdapter(Main.this, R.layout.icons_item, IconsList);

//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main.this,
//				android.R.layout.simple_list_item_1, data);
		ListView listView = (ListView) findViewById(R.id.listview_main);
		listView.setAdapter(adapter);
		dbHelper = new DatabaseHelper(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arg2 == 0) {
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					String sql = "select count(*) as c from sqlite_master where type ='table' ";
					Cursor cursor = db.rawQuery(sql, null);
					if (cursor.moveToNext()) {
						int count = cursor.getInt(0);
						if (count > 3) {
							DialogDemo.builder(Main.this, "错误信息",
									"数据库已存在，无需再次创建");
						} else {
							String sqll = "create table bookstore(id integer primary key autoincrement,bookname varchar(20),author varchar(20),price varchar(10))";
							db.execSQL(sqll);

							Toast.makeText(Main.this, "创建成功", Toast.LENGTH_LONG)
									.show();
						}
					}
				}
				if (arg2 == 1) {
					Intent intent = new Intent(Main.this, AddBooks.class);
					startActivity(intent);
				}
				if (arg2 == 2) {
					Intent intent = new Intent(Main.this, UpdateBooks.class);
					startActivity(intent);
				}
				if (arg2 == 3) {
					Intent intent = new Intent(Main.this, QueryBooks.class);
					startActivity(intent);
				}
				if (arg2 == 4) {
					Intent intent = new Intent(Main.this, DeleteBooks.class);
					startActivity(intent);
				}
				if (arg2 == 5) {
					Intent intent = new Intent(Main.this, BrowseBooks.class);
					startActivity(intent);
				}
			}
		});
		setting_about_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void initIcon() {
		// TODO Auto-generated method stub
		Icons createbd = new Icons("创建书库", R.drawable.a);
		IconsList.add(createbd);
		Icons addbook = new Icons("添加图书", R.drawable.b);
		IconsList.add(addbook);
		Icons upbook = new Icons("更新图书", R.drawable.c);
		IconsList.add(upbook);
		Icons qubook = new Icons("查询图书", R.drawable.d);
		IconsList.add(qubook);
		Icons debook = new Icons("删除图书", R.drawable.e);
		IconsList.add(debook);
		Icons brbook = new Icons("浏览图书", R.drawable.f);
		IconsList.add(brbook);
	}
}
