package com.cksrb.rebook.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cksrb.rebook.DataForm.BookData;
import com.cksrb.rebook.ListViewAdapter.ListViewAdapter;
import com.cksrb.rebook.ListViewItem;
import com.cksrb.rebook.R;

import java.net.MalformedURLException;

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
            try {
                addData(); // add Data
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

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
                Snackbar.make(view, "this is uni book", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); // Floating Action Button >> when click this, open buy_activity

        return view;
    }

    public void addData() throws MalformedURLException {
        BookData bookData = new BookData();

        ListViewItem  u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_gallery), "나는대학서적", "나는교수");
        adapter.addData(u1); // add list data
    }
}
