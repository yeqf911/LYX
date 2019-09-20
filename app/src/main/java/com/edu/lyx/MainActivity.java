package com.edu.lyx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText edtMainPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = edtMainPassword.getText().toString();
                if (!password.equals("123456")) {
                    Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, PasswordListActivity.class);
                startActivity(intent);
            }
        });

        edtMainPassword = findViewById(R.id.main_password);
    }
}
