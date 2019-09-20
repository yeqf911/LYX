package com.edu.lyx;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.lyx.db.SqliteHelper;

import java.util.ArrayList;
import java.util.Collections;

public class PasswordListActivity extends FragmentActivity {

    private ListView listView;
    private Button addPassBtn;
    private ArrayList<Password> datas = new ArrayList<>();
    private PasswordAdapter passwordAdapter;
    private SqliteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);

        addPassBtn = findViewById(R.id.add_password);

        addPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasswordListActivity.this, AddPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dbHelper = new SqliteHelper(this, SqliteHelper.DB_NAME, null, SqliteHelper.Version);
        initData();
        listView = findViewById(R.id.password_list);
        passwordAdapter = new PasswordAdapter(this, datas);
        listView.setAdapter(passwordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PasswordListActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 初始化数据，数据是从数据库读取的，数据库是sqlite
    private void initData() {
        Cursor result = dbHelper.getReadableDatabase().query(SqliteHelper.PASSWORD_TABLE,
                new String[]{"id", "title", "password"},
                null, null, null, null, null);
        this.datas.clear();
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                int id = result.getInt(0);
                String title = result.getString(1);
                String pass = result.getString(2);
                Password password = new Password(title, pass);
                this.datas.add(password);
                result.moveToNext();
            }
            Collections.reverse(datas);
            result.close();
        }
    }
}
