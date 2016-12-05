package com.cksrb.rebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cksrb.rebook.DataForm.ChatData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    EditText editText;
    Button button_Push;

    //
    private List<ChatData> chatDataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChatAdapter cAdapter;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_chat);
        setContentView(R.layout.chat_main);
        //
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        cAdapter = new ChatAdapter(chatDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);

  //      prepareData();

        editText = (EditText)findViewById(R.id.editText);
        button_Push = (Button)findViewById(R.id.button_Push);

        button_Push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatData chatData = new ChatData("User_Id", editText.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
                databaseReference.child("message").push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                editText.setText("");
            }
        });

        databaseReference.child("message").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
                if(cAdapter.getItemCount()==30){
                    chatDataList.remove(0);
                }
                chatDataList.add(chatData);
                cAdapter.notifyDataSetChanged();

                //TextView textView = (TextView)findViewById(R.id.textView);
                //textView.setText(chatData.getUserId()+"send this\n=>"+chatData.getMessage());  // adapter에 추가합니다.
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }

    private void prepareData(){
        ChatData chatData = new ChatData("1","1");
        chatDataList.add(chatData);

        chatData = new ChatData("2","2");
        chatDataList.add(chatData);

        chatData = new ChatData("3","3");
        chatDataList.add(chatData);

        chatData = new ChatData("4","4");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        chatData = new ChatData("5","5");
        chatDataList.add(chatData);

        cAdapter.notifyDataSetChanged();
    }

}
