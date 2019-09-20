package com.edu.lyx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class AddPasswordActivity extends AppCompatActivity {

    private EditText edtTitle;
    private EditText edtPassword;
    private Button storeBtn;
    private Button generateBtn;

    private EditText edtByteCount;
    private SwitchCompat scLower;
    private SwitchCompat scUpper;
    private SwitchCompat scNumber;
    private SwitchCompat scGantajhao;
    private SwitchCompat scAt;

    private static String LOWERSTR = "abcdefghijklmnopqrstuvwxyz";
    private static String UPPERSTR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String NUMBER = "1234567890";
    private static String GANTANHAO = "!";
    private static String AT = "@";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        edtTitle = findViewById(R.id.edit_title);
        edtPassword = findViewById(R.id.edit_password);
        storeBtn = findViewById(R.id.store);

        edtByteCount = findViewById(R.id.byte_count);
        scLower = findViewById(R.id.lower);
        scUpper = findViewById(R.id.upper);
        scNumber = findViewById(R.id.number);
        scGantajhao = findViewById(R.id.gantanhao);
        scAt = findViewById(R.id.at);

        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String password = edtPassword.getText().toString();
                if (password.length() < 8) {
                    String reason = "密码少于8位了";
                    startFailureActivity(reason);
                    finish();
                    return;
                }
                startOKActivity(title, password);
            }
        });

        generateBtn = findViewById(R.id.generate);
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean lower = scLower.isChecked();
                boolean upper = scUpper.isChecked();
                boolean number = scNumber.isChecked();
                boolean gantanhao = scGantajhao.isChecked();
                boolean at = scAt.isChecked();
                int byteCount = Integer.parseInt(edtByteCount.getText().toString());

                String password = generatePass(byteCount, lower, upper, number, gantanhao, at);
                edtPassword.setText(password);
            }
        });
    }

    private void startOKActivity(String title, String password) {
        Intent intent = new Intent(this, OKActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    private void startFailureActivity(String reason) {
        Intent intent = new Intent(this, FailureActivity.class);
        intent.putExtra("reason", reason);
        startActivity(intent);
    }

    private String generatePass(int byteCount, boolean lower, boolean upper, boolean number, boolean gantanhao, boolean at) {
        String tmpStr = "";
        if (lower) {
            tmpStr += LOWERSTR;
        }

        if (upper) {
            tmpStr += UPPERSTR;
        }

        if (number) {
            tmpStr += NUMBER;
        }

        if (gantanhao) {
            tmpStr += GANTANHAO;
        }

        if (at) {
            tmpStr += AT;
        }

        if (tmpStr.length() == 0) {
            return "";
        }

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteCount; i++) {
            int rand = random.nextInt(tmpStr.length());
            sb.append(tmpStr.charAt(rand));
        }

        return sb.toString();
    }
}
