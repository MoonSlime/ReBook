package com.cksrb.rebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view){
        new IntentIntegrator(this).initiateScan();
        //Intent intent = new Intent(this,ScanActivity.class);
        //startActivity(intent);
    }

    public void onClick2(View view){
        Intent intent = new Intent(this,ChatActivity.class);
        startActivity(intent);
    }

    public void onClick3(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        //QR코드/바코드를 스캔한 결과 값을 가져옴
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        //결과값 출력
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(result.getContents() + "["+ result.getFormatName()+"]")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public void test() throws IOException {
        //java코드로 특정 url에 요청보내고 응답받기
        //기본 자바 API를 활용한 방법

        String clientID="H6Vntz4GAyzdZlEZLgHq"; //네이버 개발자 센터에서 발급받은 clientID입력
        String clientSecret = "3ghV0ENhbJ";        //네이버 개발자 센터에서 발급받은 clientSecret입력
        URL url = new URL("https://openapi.naver.com/v1/search/book.xml?query=java"); //API 기본정보의 요청 url을 복사해오고 필수인 query를 적어줍니당!

        URLConnection urlConn=url.openConnection(); //openConnection 해당 요청에 대해서 쓸 수 있는 connection 객체

        urlConn.setRequestProperty("X-Naver-Client-ID", clientID);
        urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

        String msg = null;
        while((msg = br.readLine())!=null)
        {
            Log.d("msg",msg);
        }
    }

}
