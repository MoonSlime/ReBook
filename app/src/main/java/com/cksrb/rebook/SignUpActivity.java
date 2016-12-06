package com.cksrb.rebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cksrb.rebook.DataForm.USER;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    Button signup_button;

    private  AutoCompleteTextView signup_Id;
    private EditText signup_Password;
    private EditText signup_Name;
    private EditText signup_Phone;

    private ReBookApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        app=(ReBookApplication)getApplication();

        signup_Id=(AutoCompleteTextView)findViewById(R.id.signup_Id);
        signup_Password=(EditText)findViewById(R.id.signup_Password);
        signup_Name=(EditText)findViewById(R.id.signup_Name);
        signup_Phone=(EditText)findViewById(R.id.signup_Phone);

        signup_button = (Button)findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean signup=true;

                USER user = new USER(signup_Id.getText().toString(),signup_Password.getText().toString()
                ,signup_Name.getText().toString(),signup_Phone.getText().toString());

                List<USER> userList = app.getUserList();

                int i = userList.size();
                for (; i > 0; --i) {
                    if (userList.get(i-1).id.equals(user.id))signup=false;
                }

                if(signup){
                    app.databaseReference.child("UserList").push().setValue(user);
                    Toast.makeText(getApplicationContext(),"회원으로 등록되었습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(getApplicationContext(),"이미 등록된 아이디입니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
