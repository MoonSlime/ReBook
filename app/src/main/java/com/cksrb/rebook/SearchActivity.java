package com.cksrb.rebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SearchActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = (EditText)findViewById(R.id.editText_Search);
        button = (Button)findViewById(R.id.button_Search);
        textView = (TextView)findViewById(R.id.textView_Search);


        String clientId = "H6Vntz4GAyzdZlEZLgHq";
        String clientSecret = "3ghV0ENhbJ";
        try {
            String text = URLEncoder.encode("그린팩토리", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            textView.setText(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
