package com.cksrb.rebook.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cksrb.rebook.DataForm.BookData;
import com.cksrb.rebook.ListViewAdapter.ListViewAdapterDeal;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;
import com.cksrb.rebook.ReBookApplication;

import java.util.List;

public class DealFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private ReBookApplication app;

    private ListView booklist;
    private ListViewAdapterDeal adapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal, null);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
              //  android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        app = (ReBookApplication)getContext().getApplicationContext();

        adapter = new ListViewAdapterDeal(getContext());
        booklist = (ListView) view.findViewById(R.id.dealList);
        booklist.setItemsCanFocus(false);
        booklist.setAdapter(adapter);

        addData();



        return view;
    }

    public void addData(){
        adapter = new ListViewAdapterDeal(getContext());
        List<BookData> bookDataList=null;

        for(int t=0;t<2;++t) {
            if(t==0)bookDataList = app.getBookList();
            else if(t==1)bookDataList = app.getBookList_normal();

            int i = bookDataList.size();
            for (; i > 0; --i) {
                if (bookDataList.get(i - 1).getSellerId().equals(app.getUserId())) {
                    ListViewItem u = null;
                    if (bookDataList.get(i - 1).getCustomerId() != null) {
                        u = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_manage),
                                bookDataList.get(i - 1).getTitle(), "구매자 : " + bookDataList.get(i - 1).getCustomerId()
                                , bookDataList.get(i - 1).getSellerId(), bookDataList.get(i - 1).getIsbn(), bookDataList.get(i - 1).getType());
                    } else {
                        u = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_manage),
                                  bookDataList.get(i - 1).getTitle(), "구매자 : 없음"
                                , bookDataList.get(i - 1).getSellerId()
                                , bookDataList.get(i - 1).getIsbn()
                                , bookDataList.get(i - 1).getType());
                    }
                    adapter.addData(u);
                }

                if (bookDataList.get(i - 1).getCustomerId() != null && bookDataList.get(i - 1).getCustomerId().equals(app.getUserId())) {
                    ListViewItem u = null;
                    u = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_manage),
                            bookDataList.get(i - 1).getTitle(), "판매자 : " + bookDataList.get(i - 1).getSellerId()
                            , bookDataList.get(i - 1).getSellerId(), bookDataList.get(i - 1).getIsbn()
                            , bookDataList.get(i - 1).getType());

                    adapter.addData(u);
                }

            }
        }
        booklist.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        addData();
        booklist.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // 새로고침 완료
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
