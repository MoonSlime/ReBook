package com.cksrb.rebook.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cksrb.rebook.DataForm.BookData;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;
import com.cksrb.rebook.ReBookApplication;

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

        mListview = (ListViewItem) getItem(position);

        if(mListview != null){
            if(mListview.getBookCoverDrawable() != null){
                bookCoverIcon.setImageDrawable(mListview.getBookCoverDrawable());
            }

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
}
