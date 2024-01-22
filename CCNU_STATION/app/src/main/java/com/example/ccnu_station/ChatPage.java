package com.example.ccnu_station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ChatPage extends AppCompatActivity {
    private String User;
    private int ChatRoomIdInt;
    private static final String User_Identity =
            "com.example.ccnu_station.ChatPage.UserIdentity";
    private static final String Chat_RoomID =
            "com.example.ccnu_station.ChatPage.Chat_RoomId";
    public static Intent newIntent(Context packageContext,String user_Identity,int chat_RoomID)
    {
        Intent intent = new Intent(packageContext,ChatPage.class);
        intent.putExtra(User_Identity,user_Identity);
        intent.putExtra(Chat_RoomID,chat_RoomID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        User = getIntent().getStringExtra(User_Identity);
        ChatRoomIdInt = getIntent().getIntExtra(Chat_RoomID,-1);
    }
}