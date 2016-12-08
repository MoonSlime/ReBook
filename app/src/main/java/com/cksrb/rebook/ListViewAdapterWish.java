package com.cksrb.rebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Duck on 2016-12-08.
 */

public class ListViewAdapterWish extends BaseAdapter {
    private ListViewItem mListview;
    private Context mContext;

    private ImageView bookCoverIcon;
    private TextView bookNameStr;
    private TextView sellerStr;

    private ArrayList<ListViewItem> mList;

    public ListViewAdapterWish(Context context){
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
                    .inflate(R.layout.listview_item_wish, null);

            bookCoverIcon = (ImageView) view.findViewById(R.id.wishImageView);
            bookNameStr = (TextView) view.findViewById(R.id.wishBookNameTextView);
            sellerStr = (TextView) view.findViewById(R.id.wishSellerTextView);
        }

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
