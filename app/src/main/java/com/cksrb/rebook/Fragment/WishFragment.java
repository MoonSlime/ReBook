package com.cksrb.rebook.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cksrb.rebook.DataForm.WishData;
import com.cksrb.rebook.ListViewAdapter.ListViewAdapterWish;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;
import com.cksrb.rebook.ReBookApplication;

import java.util.List;

public class WishFragment extends Fragment{
    private ReBookApplication app;

    private ListView booklist;
    private ListViewAdapterWish adapter;

    private static int UNI = 1;
    private static int NORMAL = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish, null);

        app=(ReBookApplication)getContext().getApplicationContext();

        adapter = new ListViewAdapterWish(getContext());
        booklist = (ListView) view.findViewById(R.id.wishList);
        booklist.setAdapter(adapter);

        addData();

        return view;
    }

    public void addData(){
        adapter = new ListViewAdapterWish(getContext());
        List<WishData> wishDataList = app.getWishList();

        if(wishDataList!=null){
            int i=wishDataList.size();
            for(;i>0;--i){
                if(wishDataList.get(i-1).getUserId().equals(app.getUserId())){
                    ListViewItem u = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_gallery),
                            "title=no","author=no",wishDataList.get(i-1).getSellerId()
                            ,wishDataList.get(i-1).getIsbn(),wishDataList.get(i-1).getType());
                    adapter.addData(u);
                }
            }
        }


        booklist.setAdapter(adapter);
        adapter.notifyDataSetChanged();

/*
        ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_send),
                "나는 장바구니", "살거냣","","",2);
        adapter.addData(u1); // add list data
        */
    }

}
