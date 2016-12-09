package com.cksrb.rebook.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class DealFragment extends Fragment{

    private ReBookApplication app;
    private ListView booklist;
    private ListViewAdapterDeal adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal, null);

        app = (ReBookApplication)getContext().getApplicationContext();

        adapter = new ListViewAdapterDeal(getContext());
        booklist = (ListView) view.findViewById(R.id.dealList);
        booklist.setItemsCanFocus(false);
        booklist.setAdapter(adapter);

        addData();

        adapter.notifyDataSetChanged();

        return view;
    }

    public void addData(){
        List<BookData> bookDataList = app.getBookList();

        int i=bookDataList.size();
        for(;i>0;--i){
            if(bookDataList.get(i-1).getSellerId().equals(app.getUserId())){
                ListViewItem u=null;
                if(bookDataList.get(i-1).getCustomerId()!=null){
                    u = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_manage),
                            bookDataList.get(i-1).getTitle(),"구매자 : "+bookDataList.get(i-1).getCustomerId()
                            ,bookDataList.get(i-1).getSellerId(),bookDataList.get(i-1).getIsbn());
                }
                else{
                    u = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_manage),
                            bookDataList.get(i-1).getTitle(),"구매자 : 없음"
                            ,bookDataList.get(i-1).getSellerId()
                    ,bookDataList.get(i-1).getIsbn());
                }
                adapter.addData(u);
            }

            if(bookDataList.get(i-1).getCustomerId()!=null&&bookDataList.get(i-1).getCustomerId().equals(app.getUserId())){
                ListViewItem u = null;
                u = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_manage),
                        bookDataList.get(i-1).getTitle(),"판매자 : "+bookDataList.get(i-1).getSellerId()
                        ,bookDataList.get(i-1).getSellerId(),bookDataList.get(i-1).getIsbn());

                adapter.addData(u);
            }

        }
    }
}
