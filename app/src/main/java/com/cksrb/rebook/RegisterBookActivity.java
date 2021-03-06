package com.cksrb.rebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cksrb.rebook.DataForm.BookData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RegisterBookActivity extends AppCompatActivity {
    private ReBookApplication app;

    BookData book;
    private boolean check=false;

    private Button button;
    private Button button_registerBook;

    private TextView textView_title;
    private TextView textView_publisher;
    private EditText sellprice;

    private int type=1;
    private static int UNI = 1;
    private static int NORMAL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_book);

        app = (ReBookApplication)getApplication();

        //Intent intent = getIntent();
        //type = intent.getIntExtra("type",0);

        book = new BookData();

        button = (Button)findViewById(R.id.button_scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isbnScan();
            }
        });

        button_registerBook=(Button)findViewById(R.id.button_RegisterBook);
        button_registerBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerBook();
            }
        });

        textView_title=(TextView)findViewById(R.id.textView_title);
        textView_publisher=(TextView)findViewById(R.id.textView_publisher);
        sellprice=(EditText)findViewById(R.id.editTExt_sellPrice);
    }

    protected void isbnScan(){
        new IntentIntegrator(this).initiateScan();
    }
    protected void registerBook(){
        if(check){
            app.databaseReference.child("BookList").child(app.getUserId()+"|"+book.getIsbn()).setValue(book);
            Toast.makeText(getApplicationContext(),"판매 등록되었습니다.",Toast.LENGTH_SHORT);
        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        //QR코드/바코드를 스캔한 결과 값을 가져옴
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(result.getContents().toString());
        /*
        //결과값 출력
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(result.getContents() + "["+ result.getFormatName()+"]")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();*/
    }

    public class MyAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String...params) {
            String str;

            String clientId = "H6Vntz4GAyzdZlEZLgHq";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "3ghV0ENhbJ";//애플리케이션 클라이언트 시크릿값";
            try {
                // String text = URLEncoder.encode("java", "UTF-8");
                String ISBN=params[0];
                //String ISBN = "9788998756680";
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
                    book.setIsbn(ISBN);
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
                str="NULL";
            }

            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("NULL"))return ;

            JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("items");

            JsonObject jsonObject_item = jsonArray.get(0).getAsJsonObject();

            JsonPrimitive jsonPrimitive = jsonObject_item.getAsJsonPrimitive("title");
            book.setTitle(jsonPrimitive.getAsString());
            textView_title.setText(book.getTitle());

            jsonPrimitive = jsonObject_item.getAsJsonPrimitive("link");
            book.setLink(jsonPrimitive.getAsString());

            jsonPrimitive = jsonObject_item.getAsJsonPrimitive("image");
            book.setImage_url(jsonPrimitive.getAsString());

            jsonPrimitive = jsonObject_item.getAsJsonPrimitive("author");
            book.setAuthor(jsonPrimitive.getAsString());

            jsonPrimitive = jsonObject_item.getAsJsonPrimitive("price");
            book.setPrice(jsonPrimitive.getAsInt());

            jsonPrimitive = jsonObject_item.getAsJsonPrimitive("publisher");
            book.setPublisher(jsonPrimitive.getAsString());
            textView_publisher.setText(book.getPublisher());

            jsonPrimitive = jsonObject_item.getAsJsonPrimitive("description");
            book.setDescription(jsonPrimitive.getAsString());

            book.setSellerId(app.getUserId());

            book.setSellPrice(sellprice.getText().toString());

            if(type==1)book.setType(UNI);
            else if(type==2)book.setType(NORMAL);

            List<BookData> bookList = app.getBookList();
            int i;
            check=true;
            for(i=bookList.size();i>0;--i){
                if(bookList.get(i-1).getIsbn().equals(book.getIsbn())
                        &&bookList.get(i-1).getSellerId().equals(book.getSellerId())){
                    check=false;
                }
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }
}
