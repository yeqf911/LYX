package com.edu.lyx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.edu.lyx.db.SqliteHelper;

public class OKActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvPassword;
    private Button storePassBtn;

    SqliteHelper dbHelper = new SqliteHelper(this,SqliteHelper.DB_NAME, null,SqliteHelper.Version);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok);

        tvTitle = findViewById(R.id.title);
        tvPassword = findViewById(R.id.password);
        storePassBtn = findViewById(R.id.store_password);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String password = intent.getStringExtra("password");

        tvTitle.setText(title);
        tvPassword.setText(password);

        storePassBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.store_password) {
            storePassword();
            Intent intent = new Intent(OKActivity.this, PasswordListActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void storePassword() {
        String title = tvTitle.getText().toString();
        String pass = tvPassword.getText().toString();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("password", pass);
        db.insert(SqliteHelper.PASSWORD_TABLE, null, values);
        db.close();
    }
}
