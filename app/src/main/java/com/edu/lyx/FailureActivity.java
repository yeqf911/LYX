package com.edu.lyx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FailureActivity extends AppCompatActivity {

    private TextView tvReason;
    private Button rtnBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure);

        tvReason = findViewById(R.id.reason);
        rtnBackBtn = findViewById(R.id.return_back);

        Intent intent = getIntent();
        String reason = intent.getStringExtra("reason");
        tvReason.setText(reason);

        rtnBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FailureActivity.this, AddPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
