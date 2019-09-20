package com.edu.lyx.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


// 数据库帮助类
public class SqliteHelper extends SQLiteOpenHelper {
    public static Integer Version = 1;
    public static String DB_NAME = "PasswordStore.db";
    public static String PASSWORD_TABLE = "passwords";

    // 创建passwords表的SQL语句
    private static final String CREATE_PASSWORD_TABLE = "create table passwords(id integer primary key autoincrement," +
            "title varchar(64)," +
            "password varchar(64))"; // email是唯一的，name可以重复

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 第一次打开应用连接数据库的时候创建表
        sqLiteDatabase.execSQL(CREATE_PASSWORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
