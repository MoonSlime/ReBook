package com.cksrb.rebook.ListViewAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cksrb.rebook.DownLoadImageTask;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Duck on 2016-12-07.
 */

public class ListViewAdapter extends BaseAdapter{
    private ListViewItem mListview;
    private Context mContext;

    private ImageView bookCoverIcon;
    private TextView bookNameStr;
    private TextView sellerStr;

    private ArrayList<ListViewItem> mList;

    public ListViewAdapter(Context context){
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
                    .inflate(R.layout.listview_item, null);

            bookCoverIcon = (ImageView) view.findViewById(R.id.imageView1);
            bookNameStr = (TextView) view.findViewById(R.id.textV1);
            sellerStr = (TextView) view.findViewById(R.id.textV2);
        }

        mListview = (ListViewItem) getItem(position);

        if(mListview != null) {
            new DownLoadImageTask(bookCoverIcon).execute(mListview.getBookCoverUrl());

            int type = mListview.getType();
            if(type == 1) {
                bookNameStr.setText((mListview.getBookName())+"("+(mListview.getClassStr())+")");
            }
            else{
                bookNameStr.setText(mListview.getBookName());
            }
            sellerStr.setText(mListview.getSeller());
        }
        return view;
    }

    public void addData(ListViewItem bookList){
        mList.add(bookList);
    }
}
