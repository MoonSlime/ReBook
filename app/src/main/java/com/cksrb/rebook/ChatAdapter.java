package com.cksrb.rebook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cksrb.rebook.DataForm.ChatData;

import java.util.List;

/**
 * Created by cksrb on 2016. 12. 5..
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{
    private List<ChatData> chatDataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userId,message;

        public MyViewHolder(View view) {
            super(view);
            userId = (TextView) view.findViewById(R.id.user_id);
            message = (TextView) view.findViewById(R.id.message);
        }
    }


    public ChatAdapter(List<ChatData> chatDataList) {
        this.chatDataList = chatDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_in, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChatData chatData = chatDataList.get(position);
        holder.userId.setText(chatData.getFrom());
        holder.message.setText(chatData.getMessage());
    }

    @Override
    public int getItemCount() {
        return chatDataList.size();
    }
}