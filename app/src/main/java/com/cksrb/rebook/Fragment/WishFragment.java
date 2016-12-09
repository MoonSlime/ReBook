package com.cksrb.rebook.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cksrb.rebook.ListViewAdapter.ListViewAdapterWish;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;

public class WishFragment extends Fragment{
        private ListView booklist;
        private ListViewAdapterWish adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish, null);

        adapter = new ListViewAdapterWish(getContext());
        booklist = (ListView) view.findViewById(R.id.wishList);
        booklist.setAdapter(adapter);

        for(int i = 1; i < 10; i++) {
            addData(); // add Data
        }

        adapter.notifyDataSetChanged();

        return view;
    }

    public void addData(){
        ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_send),
                "나는 장바구니", "살거냣","");
        adapter.addData(u1); // add list data
    }
}
