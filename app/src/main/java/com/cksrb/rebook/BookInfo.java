package com.cksrb.rebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookInfo extends AppCompatActivity {
    private ReBookApplication app;

    private String isbn;
    private String sellerId;
    private String title;
    private String type;


    private TextView titleInfo;
    private Button buyBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        app=(ReBookApplication)getApplication();

        Intent intent = getIntent();

        isbn = intent.getStringExtra("isbn");
        sellerId = intent.getStringExtra("sellerId");

        title=intent.getStringExtra("title");

        titleInfo = (TextView)findViewById(R.id.titleInfo);
        titleInfo.setText(title);

        buyBook = (Button)findViewById(R.id.button_BuyBook);
        buyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!app.getUserId().equals(sellerId))
                    type="Uni";
                    app.databaseReference.child("BookList").child(type).child(sellerId+"|"+isbn)
                        .child("customerId").setValue(app.getUserId());

                Toast.makeText(getApplicationContext(),"거래목록에 추가되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
