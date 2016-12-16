package com.cksrb.rebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cksrb.rebook.DataForm.WishData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BookInfo extends AppCompatActivity {
    private ReBookApplication app;

    private String isbn;
    private String sellerId;
    private String bookcover;
    private String title;
    private String publisher;
    private int price;
    private String sellerPrice;
    private String etc;
    private int type;

    private ImageView bookcoverInfo;
    private TextView titleInfo;
    private TextView publisherInfo;
    private TextView priceInfo;
    private TextView sellerPriceInfo;
    private TextView etcInfo;

    private Button buyBook;
    private Button wishBook;

    private static int UNI = 1;
    private static int NORMAL = 0;

    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        app=(ReBookApplication)getApplication();

        Intent intent = getIntent();

        isbn = intent.getStringExtra("isbn");
        sellerId = intent.getStringExtra("sellerId");
        type = intent.getIntExtra("type",1);

        bookcover = intent.getStringExtra("url");
        bookcoverInfo = (ImageView)findViewById(R.id.imageInfo); // have to modify
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    bitmap = getBitmap(bookcover);
                }catch (Exception e){

                }finally {
                    if(bitmap != null){
                        runOnUiThread(new Runnable() {
                            @SuppressLint("NewApi")
                            public void run() {
                                bookcoverInfo.setImageBitmap(bitmap);
                            }
                        });
                    }
                }
            }
        }).start();

        title=intent.getStringExtra("title");
        titleInfo = (TextView)findViewById(R.id.titleInfo);
        titleInfo.setText(title);
        titleInfo.setSelected(true);

        publisher=intent.getStringExtra("publisher");
        publisherInfo=(TextView)findViewById(R.id.publisherInfo);
        publisherInfo.setText(publisher);

        price = intent.getIntExtra("price",1);
        priceInfo=(TextView)findViewById(R.id.priceInfo);
        priceInfo.setText(""+price);

        sellerPrice = intent.getStringExtra("sellerPrice");
        sellerPriceInfo=(TextView)findViewById(R.id.sellPriceInfo);
        sellerPriceInfo.setText(sellerPrice);

        etc = intent.getStringExtra("etc");
        etcInfo = (TextView)findViewById(R.id.etcText);
        etcInfo.setText(etc);

        wishBook=(Button)findViewById(R.id.button_WishBook);
        wishBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IWishYourMerryChristmas();
            }
        });

        buyBook = (Button)findViewById(R.id.button_BuyBook);
        buyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!app.getUserId().equals(sellerId)) {
                    String str="Normal";
                    if(type==UNI)str="Uni";
                    app.databaseReference.child("BookList").child(str).child(sellerId + "|" + isbn)
                            .child("customerId").setValue(app.getUserId());

                    Toast.makeText(getApplicationContext(), "거래목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"자신이 등록한 도서는 구매하실수 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void IWishYourMerryChristmas(){
        List<WishData> wishList = app.getWishList();

        if(wishList==null)return ;

        boolean check=true;

        int i = wishList.size();
        for(;i>0;--i){
            if(wishList.get(i-1).getSellerId().equals(sellerId)&&wishList.get(i-1).getIsbn().equals(isbn)){
                check=false;
                break;
            }
        }

         if(check){
            WishData wishData = new WishData(app.getUserId(),isbn,sellerId,type);
            app.databaseReference.child("WishList").child(app.getUserId()+"|"+isbn).setValue(wishData);
            Toast.makeText(getApplicationContext(),"장바구니에 추가되었습니다.",Toast.LENGTH_SHORT).show();
            finish();
        }
        else Toast.makeText(getApplicationContext(),"장바구니에 추가할수없습니다.",Toast.LENGTH_SHORT).show();

    }

    public Bitmap getBitmap(String url) {
        URL imgUrl = null;
        HttpURLConnection connection = null;
        InputStream is = null;

        Bitmap retBitmap = null;

        try {
            imgUrl = new URL(url);
            connection = (HttpURLConnection) imgUrl.openConnection();
            connection.setDoInput(true); //url로 input받는 flag 허용
            connection.connect(); //연결
            is = connection.getInputStream(); // get inputstream
            retBitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return retBitmap;
        }
    }
}
