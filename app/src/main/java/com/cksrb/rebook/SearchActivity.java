package com.cksrb.rebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    EditText editText = (EditText)findViewById(R.id.editText_Search);
    Button button = (Button)findViewById(R.id.button_Search);
    TextView textView = (TextView)findViewById(R.id.textView_Search);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
