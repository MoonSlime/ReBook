package com.cksrb.rebook.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cksrb.rebook.ListViewAdapter.ListViewAdapter;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;
import com.cksrb.rebook.ReBookApplication;
import com.cksrb.rebook.RegisterBookActivity;

public class UniFragment extends Fragment {
    ReBookApplication app;

    private ListView booklist;
    private ListViewAdapter adapter;

    private Button button_Search ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_uni, null);

        app=(ReBookApplication)  getContext().getApplicationContext();

        adapter = new ListViewAdapter(getContext());
        booklist = (ListView) view.findViewById(R.id.uniList);
        booklist.setAdapter(adapter);

        button_Search = (Button) view.findViewById(R.id.button_Search);
        button_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyDataSetChanged();
            }
        });

        addData(); // add Data

        AdapterView.OnItemClickListener listViewClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "abc"+position, Toast.LENGTH_SHORT).show();
            }
        }; // when click list, open new activity(book info)

        booklist.setOnItemClickListener(listViewClickListener);
        adapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabUni);
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
        ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_gallery),app.getBookList().get(0).getTitle()
                ,app.getBookList().get(0).getAuthor());
       // ListViewItem  u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_gallery), "나는대학서적", "나는교수");
        adapter.addData(u1); // add list data
    }
}
