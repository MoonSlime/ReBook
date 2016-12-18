package com.cksrb.rebook.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cksrb.rebook.BookInfo;
import com.cksrb.rebook.DataForm.BookData;
import com.cksrb.rebook.ListViewAdapter.ListViewAdapter;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;
import com.cksrb.rebook.ReBookApplication;
import com.cksrb.rebook.RegisterBookActivity;

import java.util.List;

public class NormalFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private ReBookApplication app;

    private ListView booklist;
    private ListViewAdapter adapter;

    private EditText editText_Search;
    private Button button_Search;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static int NORMAL = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal, null);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                //  android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        app=(ReBookApplication)getContext().getApplicationContext();

        adapter = new ListViewAdapter(getContext());
        booklist = (ListView) view.findViewById(R.id.normalList);
        booklist.setAdapter(adapter);

        editText_Search = (EditText)view.findViewById(R.id.editText_Search);
        button_Search = (Button) view.findViewById(R.id.button_Search);
        button_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData(editText_Search.getText().toString());
            }
        });

        searchData(""); // add Data

        AdapterView.OnItemClickListener listViewClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),BookInfo.class);
                intent.putExtra("isbn",((ListViewItem)adapter.getItem(position)).getIsbn());
                intent.putExtra("sellerId",((ListViewItem)adapter.getItem(position)).getSellerId());
                intent.putExtra("title",((ListViewItem)adapter.getItem(position)).getBookName());
                intent.putExtra("type",((ListViewItem)adapter.getItem(position)).getType());
                intent.putExtra("publisher",((ListViewItem) adapter.getItem(position)).getPublisher());
                intent.putExtra("price",((ListViewItem) adapter.getItem(position)).getPriceInt());
                intent.putExtra("sellerPrice",((ListViewItem) adapter.getItem(position)).getSellerPriceStr());
                intent.putExtra("etc", ((ListViewItem) adapter.getItem(position)).getEtcStr());
                intent.putExtra("url",((ListViewItem) adapter.getItem(position)).getBookCoverUrl());
                startActivity(intent);
            }
        }; // when click list, open new activity(book info)
        booklist.setOnItemClickListener(listViewClickListener);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabNormal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterBookActivity.class);
                startActivity(intent);
            }
        }); // Floating Action Button >> when click this, open buy_activity

        return view;
    }
    public void searchData(String search){
        adapter = new ListViewAdapter(getContext());
        List<BookData> bookDataList = app.getBookList_normal();

        if(bookDataList!=null) {
            int i = bookDataList.size();
            for (; i > 0; --i) {

                if (bookDataList.get(i - 1).search(search)&&bookDataList.get(i-1).getCustomerId()==null) {
                    ListViewItem u1 = new ListViewItem(bookDataList.get(i-1).getImage_url(), bookDataList.get(i - 1).getTitle()
                            , bookDataList.get(i - 1).getAuthor(), bookDataList.get(i - 1).getSellerId(), bookDataList.get(i - 1).getIsbn(),NORMAL
                    ,bookDataList.get(i-1).getPublisher(), bookDataList.get(i-1).getPrice(), bookDataList.get(i-1).getSellPrice(), bookDataList.get(i-1).getEtc());
                    adapter.addData(u1); // add list data
                }
            }
        }

        booklist.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        searchData(editText_Search.getText().toString());

        // 새로고침 완료
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
