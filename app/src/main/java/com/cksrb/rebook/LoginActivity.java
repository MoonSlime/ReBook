package com.cksrb.rebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cksrb.rebook.DataForm.USER;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    //private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private ReBookApplication app;

    private AutoCompleteTextView login_Id;
    private EditText login_Password;
    private Button login_Button;
    private Button signup_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app = (ReBookApplication) getApplication();

        login_Id = (AutoCompleteTextView) findViewById(R.id.login_Id);
        login_Password = (EditText) findViewById(R.id.login_Password);
        login_Button = (Button) findViewById(R.id.login_button);
        signup_Button = (Button) findViewById(R.id.signup_button);

        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptLogin(login_Id.getText().toString(), login_Password.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    //Intent intent = new Intent(getApplicationContext(),RegisterBookActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
             }
        });

    }

    public boolean attemptLogin(String id, String password) {
        List<USER> userList = app.getUserList();
        int i = userList.size();
        for (; i > 0; --i) {
            if (userList.get(i-1).equals(new USER(id, password))) {
                app.setUserId(userList.get(i-1).getId());
                return true;
            }
        }

        Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();

        return false;
    }

}

