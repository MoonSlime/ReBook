package com.cksrb.rebook.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cksrb.rebook.DataForm.BookData;
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

       // searchData(""); // add Data

        return view;
    }

    public void searchData(String search){
        adapter = new ListViewAdapterWish(getContext());
        List<BookData> bookDataList = app.getBookList();

        if(bookDataList!=null) {
            int i = bookDataList.size();
            for (; i > 0; --i) {

                if (bookDataList.get(i - 1).search(search)&&bookDataList.get(i-1).getCustomerId()==null) {
                    ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_gallery), bookDataList.get(i - 1).getTitle()
                            , bookDataList.get(i - 1).getAuthor(), bookDataList.get(i - 1).getSellerId(), bookDataList.get(i - 1).getIsbn(),UNI);
                    adapter.addData(u1); // add list data
                }
            }
        }

        booklist.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addData(){
        adapter = new ListViewAdapterWish(getContext());
        List<WishData> wishDataList = app.getWishList();

        if(wishDataList!=null){

        }


        booklist.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        ListViewItem u1 = new ListViewItem(getResources().getDrawable(R.drawable.ic_menu_send),
                "나는 장바구니", "살거냣","","",2);
        adapter.addData(u1); // add list data
    }
}
