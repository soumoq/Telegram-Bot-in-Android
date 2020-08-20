package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ajts.androidmads.telegrambotlibrary.models.Message;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ajts.androidmads.telegrambotlibrary.Telegram;
import com.ajts.androidmads.telegrambotlibrary.Utils.TelegramCallback;
import com.ajts.androidmads.telegrambotlibrary.models.GetMe;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    Telegram telegram;
    EditText editText;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            editText = findViewById(R.id.edit_text);
            Button button = findViewById(R.id.send);


            try {

                telegram = new Telegram("1373647184:AAFyOYDCVa1JutzndChRQWTxhzWkNFKj_Ws");

                telegram.getMe(new TelegramCallback<GetMe>() {
                    @Override
                    public void onResponse(Call call, final GetMe getMe) {
                        Log.v("response.body()", getMe.isOk() + "");

                    }

                    @Override
                    public void onFailure(Call call, Exception e) {
                        Toast.makeText(MainActivity.this, "error: " + e, Toast.LENGTH_LONG).show();

                    }
                });


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        msg = editText.getText().toString();
                        telegram.sendMessage("1018359425", msg, new TelegramCallback<Message>() {
                            @Override
                            public void onResponse(Call call, Message response) {
                                Log.v("response.body()", response.isOk() + "");
                                editText.setText(null);
                            }

                            @Override
                            public void onFailure(Call call, Exception e) {
                                Toast.makeText(MainActivity.this, "error: " + e, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });


            } catch (Exception e) {
                Toast.makeText(this, "Error: ", Toast.LENGTH_LONG).show();
            }


        } catch (Exception e) {
            Toast.makeText(this, "Error: ", Toast.LENGTH_LONG).show();
        }
    }
}