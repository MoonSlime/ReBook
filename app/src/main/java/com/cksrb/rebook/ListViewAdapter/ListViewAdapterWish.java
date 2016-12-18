package com.cksrb.rebook.ListViewAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cksrb.rebook.DataForm.BookData;
import com.cksrb.rebook.DownLoadImageTask;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;
import com.cksrb.rebook.ReBookApplication;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duck on 2016-12-08.
 */

public class ListViewAdapterWish extends BaseAdapter {
    private ReBookApplication app;

    private ListViewItem mListview;
    private Context mContext;

    private ImageView bookCoverIcon;
    private TextView bookNameStr;
    private TextView sellerStr;

    private Button wishBuyBtn;
    private Button wishCancelBtn;

    private ArrayList<ListViewItem> mList;

    private static int UNI = 1;
    private static int NORMAL = 0;

    public ListViewAdapterWish(Context context){
        super();
        mContext=context;
        app=(ReBookApplication)mContext.getApplicationContext();
        mList = new ArrayList<ListViewItem>();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.listview_item_wish, null);
        }

        bookCoverIcon = (ImageView) view.findViewById(R.id.wishImageView);
        bookNameStr = (TextView) view.findViewById(R.id.wishBookNameTextView);
        sellerStr = (TextView) view.findViewById(R.id.wishSellerTextView);

        wishBuyBtn = (Button)view.findViewById(R.id.wishBuyBtn);
        wishBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBuy();
            }
        });

        wishCancelBtn = (Button) view.findViewById(R.id.wishCancelBtn);
        wishCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wishCancel();
            }
        });

        mListview = (ListViewItem) getItem(position);

        if(mListview != null){
            String bookName=null,sellerId=null;
            List<BookData> bookList = null;
            if(mListview.getType()==UNI){
                bookList=app.getBookList();
            }
            else if(mListview.getType()==NORMAL){
                bookList=app.getBookList_normal();
            }
            
            int i=bookList.size();
            for(;i>0;--i){
                if(bookList.get(i-1).getIsbn().equals(mListview.getIsbn())
                        &&bookList.get(i-1).getSellerId().equals(mListview.getSellerId())){
                    new DownLoadImageTask(bookCoverIcon).execute(bookList.get(i-1).getImage_url());

                    bookName=bookList.get(i-1).getTitle();
                    sellerId=bookList.get(i-1).getSellerId();
                }
            }

            bookNameStr.setText(bookName);
            sellerStr.setText(sellerId);
        }

        return view;
    }

    public void addData(ListViewItem bookList){
        mList.add(bookList);
    }

    public void startBuy(){
        String type ="Normal";
        if(mListview.getType()==1)type="Uni";

        String str=mListview.getSellerId()+"|"+mListview.getIsbn();

        app.databaseReference.child("BookList").child(type).child(str).child("customerId").setValue(app.getUserId());

        String cmpstr=app.getUserId()+"|"+mListview.getIsbn();
        app.databaseReference.child("WishList").child(cmpstr).setValue(null);
        Toast.makeText(mContext,"거래현황에 추가되었습니다.",Toast.LENGTH_SHORT).show();
    }

    public void wishCancel(){
        String str=app.getUserId()+"|"+mListview.getIsbn();

        app.databaseReference.child("WishList").child(str).setValue(null);
        Toast.makeText(mContext,"장바구니가 취소되었습니다.",Toast.LENGTH_SHORT).show();
    }
}
