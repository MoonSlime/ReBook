package com.cksrb.rebook.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cksrb.rebook.ListViewAdapter.ListViewAdapter;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;

public class NormalFragment extends Fragment {
    private ListView booklist;
    private ListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal, null);

        adapter = new ListViewAdapter(getContext());
        booklist = (ListView) view.findViewById(R.id.normalList);
        booklist.setAdapter(adapter);

        for(int i = 1; i < 10; i++) {
            addData(); // add Data
        }

        adapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabNormal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "this is normal book", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); // Floating Action Button >> when click this, open buy_activity

        return view;
    }

    public void addData(){
        ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_gallery),
                "나는책이름", "나는작가");
        adapter.addData(u1); // add list data
    }
}
