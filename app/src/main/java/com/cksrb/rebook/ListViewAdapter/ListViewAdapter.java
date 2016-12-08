package com.cksrb.rebook.ListViewAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;

import org.w3c.dom.Text;

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
