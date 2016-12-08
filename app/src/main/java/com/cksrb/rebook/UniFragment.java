package com.cksrb.rebook;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UniFragment extends Fragment {
    private ListView booklist;
    private ListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_uni, null);

        adapter = new ListViewAdapter(getContext());
        booklist = (ListView) view.findViewById(R.id.uniList);
        booklist.setAdapter(adapter);

        for(int i = 1; i < 10; i++) {
            addData(); // add Data
        }

        adapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabUni);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "this is uni book", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); // Floating Action Button >> when click this, open buy_activity

        return view;
    }

    public void addData(){
        ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_camera),
                "해리포터", "롤링");
        adapter.addData(u1); // add list data
    }
}
