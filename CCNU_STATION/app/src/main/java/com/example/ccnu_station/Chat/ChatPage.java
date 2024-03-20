package com.example.ccnu_station.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.ccnu_station.R;

public class ChatPage extends AppCompatActivity {
    private String User_token;
    private int ChatRoomIdInt;
    /*private static final String User_Identity =
            "com.example.ccnu_station.Chat.ChatPage.UserIdentity";

     */
    private static final String Chat_RoomID =
            "com.example.ccnu_station.Chat.ChatPage.Chat_RoomId";
    public static Intent newIntent(Context packageContext,int chat_RoomID)
    {
        Intent intent = new Intent(packageContext,ChatPage.class);
        //intent.putExtra(User_Identity,user_Identity);
        intent.putExtra(Chat_RoomID,chat_RoomID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        SharedPreferences sp = getSharedPreferences("User_Details",Context.MODE_PRIVATE);
        User_token = sp.getString("token","null");
        ChatRoomIdInt = getIntent().getIntExtra(Chat_RoomID,-1);
    }
}