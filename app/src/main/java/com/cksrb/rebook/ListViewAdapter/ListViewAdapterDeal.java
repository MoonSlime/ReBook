package com.cksrb.rebook.ListViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cksrb.rebook.ChatActivity;
import com.cksrb.rebook.DataForm.BookData;
import com.cksrb.rebook.DownLoadImageTask;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;
import com.cksrb.rebook.ReBookApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duck on 2016-12-08.
 */

public class ListViewAdapterDeal extends BaseAdapter{
    private ReBookApplication app;

    private ListViewItem mListview;
    private Context mContext;

    private ImageView bookCoverIcon;
    private TextView bookNameStr;
    private TextView sellerStr;

    private ArrayList<ListViewItem> mList;

    public ListViewAdapterDeal(Context context){
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
                    .inflate(R.layout.listview_item_deal, null);
        }

        bookCoverIcon = (ImageView) view.findViewById(R.id.dealImageView);
        bookNameStr = (TextView) view.findViewById(R.id.dealBookNameTextView);
        sellerStr = (TextView) view.findViewById(R.id.dealSellerTextView);

        Button btnBuy = (Button) view.findViewById(R.id.dealBuyBtn);
        btnBuy.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                startChat();
            }
        });

        Button btnOk = (Button) view.findViewById(R.id.dealOkBtn);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_sell();
                Toast.makeText(mContext,"거래가 완료되었습니다.",Toast.LENGTH_SHORT).show();
                // 거래완료
            }
        });

        final Button btnCancel = (Button) view.findViewById(R.id.dealCancelBtn);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnCancel.getText().equals("판매 취소")){
                    cancel_sell();
                    Toast.makeText(mContext,"판매를 취소하였습니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    onClick_cancel();
                }
            }
        });

        mListview = (ListViewItem) getItem(position);
        Log.d("andoirddebug","position  = "+position);

        if(mListview != null){
            new DownLoadImageTask(bookCoverIcon).execute(mListview.getBookCoverUrl());

            bookNameStr.setText(mListview.getBookName());
            sellerStr.setText(mListview.getSeller());

            if(mListview.getSellerId().equals(app.getUserId())){
                if(sellerStr.getText().toString().equals("구매자 : 없음"))
                    btnCancel.setText("판매 취소");
            }
        }

        return view;
    }

    public void addData(ListViewItem bookList){
        mList.add(bookList);
    }

    public void startChat(){
        String othersId=null;
        if(app.getUserId().equals(mListview.getSellerId())){
            List<BookData> bookDataList=null;
            String type=null;
            if(mListview.getType()==1) {
                type="Uni";
                bookDataList = app.getBookList();
            }
            else if(mListview.getType()==0){
                type="Normal";
                bookDataList = app.getBookList_normal();
            }

            int i=bookDataList.size();
            for(;i>0;--i){
                if(bookDataList.get(i-1).getSellerId().equals(mListview.getSellerId())
                        &&bookDataList.get(i-1).getIsbn().equals(mListview.getIsbn())){
                    othersId=bookDataList.get(i-1).getCustomerId();
                }
            }
        }
        else othersId=mListview.getSellerId();


        if(othersId!=null) {
            Intent intent = new Intent(mContext, ChatActivity.class);
            intent.putExtra("othersId", othersId);
            mContext.startActivity(intent);
        }
        else{
            Toast.makeText(mContext,"구매자가 없어 채팅을 할수 없습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel_sell(){

        List<BookData> bookDataList=null;
        String type=null;
        if(mListview.getType()==1) {
            type="Uni";
            bookDataList = app.getBookList();
        }
        else if(mListview.getType()==0){
            type="Normal";
            bookDataList = app.getBookList_normal();
        }

        Log.d("androiddebug","ISBN=  "+mListview.getIsbn());
        app.databaseReference.child("BookList").child(type)
                .child(mListview.getSellerId()+"|"+mListview.getIsbn()).setValue(null);
    }


    public void onClick_cancel(){
        String othersId=null;
        if(app.getUserId().equals(mListview.getSellerId())){
            List<BookData> bookDataList=null;
            String type=null;
            if(mListview.getType()==1) {
                type="Uni";
                bookDataList = app.getBookList();
            }
            else if(mListview.getType()==0){
                type="Normal";
                bookDataList = app.getBookList_normal();
            }

            int i=bookDataList.size();
            for(;i>0;--i){
                if(bookDataList.get(i-1).getSellerId().equals(mListview.getSellerId())
                        &&bookDataList.get(i-1).getIsbn().equals(mListview.getIsbn())){
                    othersId=bookDataList.get(i-1).getCustomerId();
                }
            }
        }
        else othersId=mListview.getSellerId();

        List<BookData> bookDataList=null;
        String type=null;
        if(mListview.getType()==1) {
            type="Uni";
            bookDataList = app.getBookList();
        }
        else if(mListview.getType()==0){
            type="Normal";
            bookDataList = app.getBookList_normal();
        }

        int i=bookDataList.size();
        for(;i>0;--i){
            if(bookDataList.get(i-1).getSellerId().equals(mListview.getSellerId())
                    &&bookDataList.get(i-1).getIsbn().equals(mListview.getIsbn())){

                app.databaseReference.child("BookList").child(type)
                        .child(mListview.getSellerId()+"|"+mListview.getIsbn()).child("customerId").setValue(null);

                String cmpstr=null;
                if(app.getUserId().compareTo(othersId)>0)cmpstr=othersId+"|"+app.getUserId();
                else cmpstr=app.getUserId()+"|"+othersId;
                app.databaseReference.child("ChatData").child(cmpstr).setValue(null);

                if(type.equals("Uni")){
                    app.getBookList().get(i-1).setCustomerId(null);
                }
                else{
                    app.getBookList_normal().get(i-1).setCustomerId(null);
                }
                Toast.makeText(mContext,"거래가 취소되었습니다.",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
