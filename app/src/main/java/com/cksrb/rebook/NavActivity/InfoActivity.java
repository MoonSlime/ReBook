package com.cksrb.rebook.NavActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cksrb.rebook.R;

public class InfoActivity extends AppCompatActivity {
    private TextView name;
    private TextView id;
    private TextView phone;
    private TextView password;

    private String nameStr;
    private String idStr;
    private String phoneStr;
    private String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();

        name = (TextView) findViewById(R.id.userNameInfo);
        nameStr = intent.getStringExtra("name");
        name.setText(nameStr);

        id = (TextView) findViewById(R.id.userIdInfo);
        idStr = intent.getStringExtra("id");
        id.setText(idStr);

        phone = (TextView)findViewById(R.id.userPhoneInfo);
        phoneStr = intent.getStringExtra("phone");
        phone.setText(phoneStr);

        password = (TextView) findViewById(R.id.userPasswordInfo);
        passwordStr = intent.getStringExtra("password");
        password.setText(passwordStr);

    }
}
