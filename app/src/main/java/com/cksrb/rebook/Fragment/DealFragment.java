package com.cksrb.rebook.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cksrb.rebook.ListViewAdapter.ListViewAdapterDeal;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;

public class DealFragment extends Fragment{
        private ListView booklist;
        private ListViewAdapterDeal adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal, null);

        adapter = new ListViewAdapterDeal(getContext());
        booklist = (ListView) view.findViewById(R.id.dealList);
        booklist.setAdapter(adapter);

        addData();

        adapter.notifyDataSetChanged();

        return view;
    }

    public void addData(){
        ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_manage),
                "나는 거래현황", "채팅해랏");
        adapter.addData(u1); // add list data
    }
}
