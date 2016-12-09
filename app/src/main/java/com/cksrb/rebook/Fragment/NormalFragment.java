package com.cksrb.rebook.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

public class NormalFragment extends Fragment{
    private ListView booklist;
    private ListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal, null);

        adapter = new ListViewAdapter(getContext());
        booklist = (ListView) view.findViewById(R.id.normalList);
        booklist.setAdapter(adapter);

        // add data

        AdapterView.OnItemClickListener listViewClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), BookInfo.class));
            }
        }; // when click list, open new activity(book info)
        booklist.setOnItemClickListener(listViewClickListener);

        adapter.notifyDataSetChanged();

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

    public void addData(){
        ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_gallery),
                "나는책이름", "나는작가","","");
        adapter.addData(u1); // add list data
    }

}
