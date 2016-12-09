package com.cksrb.rebook.ListViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cksrb.rebook.ChatActivity;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;
import com.cksrb.rebook.RegisterBookActivity;

import java.util.ArrayList;

/**
 * Created by Duck on 2016-12-08.
 */

public class ListViewAdapterDeal extends BaseAdapter{
    private ListViewItem mListview;
    private Context mContext;

    private ImageView bookCoverIcon;
    private TextView bookNameStr;
    private TextView sellerStr;

    private ArrayList<ListViewItem> mList;

    public ListViewAdapterDeal(Context context){
        super();
        mContext=context;
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

        Button btn = (Button) view.findViewById(R.id.dealBuyBtn);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);

                Intent intent = new Intent(mContext, ChatActivity.class);
                mContext.startActivity(intent);
            }
        });

        mListview = (ListViewItem) getItem(position);

        if(mListview != null){
            if(mListview.getBookCoverDrawable() != null){
                bookCoverIcon.setImageDrawable(mListview.getBookCoverDrawable());
            }
            bookNameStr.setText(mListview.getBookName());
            sellerStr.setText(mListview.getSeller());
        }

        return view;
    }

    public void addData(ListViewItem bookList){
        mList.add(bookList);
    }
}
