package cn.edu.jssvc.hzqbookmanagement_v5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.jssvc.hzqbookmanagement_v5.DatabaseHelper;
import cn.edu.jssvc.hzqbookmanagement_v5.User;

public class UserService {
	private DatabaseHelper dbHelper;

	public UserService(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	// 登录用
	public boolean login(String username, String password) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from user where username=? and password=?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { username, password });
		if (cursor.moveToFirst() == true) {
			cursor.close();
			return true;
		}
		return false;
	}

	// 注册用
	public boolean register(User user) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "insert into user(username,password) values(?,?)";
		Object obj[] = { user.getUsername(), user.getPassword() };
		sdb.execSQL(sql, obj);
		return true;
	}

	// 添加用
	public boolean add(String bookname, String author, String price) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from bookstore where bookname=? and author=? and price=?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { bookname, author,
				price });
		if (cursor.moveToFirst() == true) {
			cursor.close();
			return true;
		}
		return false;
	}
}
