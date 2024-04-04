package com.example.ccnu_station.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.CCNU_Application;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class ChatPage extends AppCompatActivity {
    private String User_token;
    private int ChatRoomIdInt;
    /*private static final String User_Identity =
            "com.example.ccnu_station.Chat.ChatPage.UserIdentity";

     */
    private WebSocket webSocket;
    private String userId = "123"; // 例如，要传递的用户ID
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
        User_token = CCNU_Application.getUser_Token();
        ChatRoomIdInt = getIntent().getIntExtra(Chat_RoomID,-1);
        OkHttpClient client = new OkHttpClient();
        String url = "ws://your_chat_server_url?user_id=" + userId; // 将用户ID作为查询参数传递

        Request request = new Request.Builder()
                .url(url)
                .build();

        WebSocketListener socketListener = new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                // 连接建立成功
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                // 接收到消息
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                // 接收到二进制消息
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                // 连接关闭
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                // 连接失败
            }
        };

        webSocket = client.newWebSocket(request, socketListener);
    }
    private void sendMessage(String message) {
        webSocket.send(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webSocket.close(1000, "Activity destroyed");
    }

}