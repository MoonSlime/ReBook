package com.cksrb.rebook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class CancelActivity extends Activity {

    private ReBookApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cancel);

        app = (ReBookApplication)getApplication();

        
    }
}
