package com.cksrb.rebook;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {
    private MyAsyncTask myAsyncTask;

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

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("80", "90", "100", "110");
    }

    public class MyAsyncTask extends AsyncTask<String,Void,String> {
        Gson gson;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String...params) {
            String str = "Hello";

            String clientId = "H6Vntz4GAyzdZlEZLgHq";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "3ghV0ENhbJ";//애플리케이션 클라이언트 시크릿값";
            try {
               // String text = URLEncoder.encode("java", "UTF-8");
                String ISBN = "9788998756680";
                String apiURL = "https://openapi.naver.com/v1/search/book_adv?d_isbn="+ISBN;
                //String apiURL = "https://openapi.naver.com/v1/search/book_adv?query="+ text+"&d_titl=java";//도서 검색
                //String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
                //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
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
                str=response.toString();
            } catch (Exception e) {
                str=""+e;
            }

            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            gson = new Gson();
            //BookData bookData = gson.fromJson(result,BookData.class);

            /*JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject)jsonParser.parse(result);
            JsonArray item =(JsonArray)jsonObject.get("item");

            textView.setText(item.getAsString());
*/
            textView.setText(result);

            //super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }

}
