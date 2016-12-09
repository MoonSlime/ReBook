package com.cksrb.rebook;

import android.app.Application;
import android.widget.Toast;

import com.cksrb.rebook.DataForm.BookData;
import com.cksrb.rebook.DataForm.USER;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cksrb on 2016. 12. 5..
 */

public class ReBookApplication extends Application {
    public int cnt=0;
    public FirebaseDatabase firebaseDatabase ;//= FirebaseDatabase.getInstance();
    public DatabaseReference databaseReference ;//= firebaseDatabase.getReference();

    private String userId;
    private List<USER> userList = new ArrayList<>();
    private List<BookData> bookList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        init();
   }

    ReBookApplication(){
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public String getUserId(){
        return userId;
    }

    public List<USER> getUserList() {
        return userList;
    }

    public List<BookData> getBookList() {
        return bookList;
    }

    private void init(){
        databaseReference.child("UserList").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                userList.add(dataSnapshot.getValue(USER.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                USER user = dataSnapshot.getValue(USER.class);
                int i=userList.size();

                for(;i>0;--i){
                    if(userList.get(i-1).getId().equals(user.getId())){
                        userList.set(i-1,user);
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                USER user = dataSnapshot.getValue(USER.class);

                int i=userList.size();
                for(;i>0;--i){
                    if(userList.get(i-1).equals(user))userList.remove(i-1);
                    break;
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        databaseReference.child("BookList").child("Uni").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                bookList.add(dataSnapshot.getValue(BookData.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                BookData book = dataSnapshot.getValue(BookData.class);

                int i=bookList.size();
                for(;i>0;--i){
                    if(bookList.get(i-1).getIsbn().equals(book.getIsbn())
                        &&bookList.get(i-1).getSellerId().equals(book.getSellerId())){
                        bookList.get(i-1).setCustomerId(book.getCustomerId());
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                BookData book = dataSnapshot.getValue(BookData.class);

                int i=bookList.size();
                for(;i>0;--i){
                    if(bookList.get(i-1).getIsbn().equals(book.getIsbn())
                            &&bookList.get(i-1).getSellerId().equals(book.getSellerId())){
                        bookList.remove(i-1);
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void debug(String str) {
        Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT);
    }

}


