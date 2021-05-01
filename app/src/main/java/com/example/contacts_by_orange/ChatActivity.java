package com.example.contacts_by_orange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;


public class ChatActivity extends Activity {
    public static int kol=100;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference(ArrayAd.nomForChat);
    ArrayList<String> messages=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_contact);
        Button back = findViewById(R.id.backtoinfo), send = findViewById(R.id.sendm);
        final EditText messagetext = findViewById(R.id.message);
        ListView chatT = findViewById(R.id.chat);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(ChatActivity.this, InfoContactActivity.class);
                startActivity(intent);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mes=messagetext.getText().toString();
                String mest=mes.trim();
                if(mest.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Введите сообщение", Toast.LENGTH_LONG).show();
                    return;
                }
                if(mes.length()>kol)
                {
                    Toast.makeText(getApplicationContext(), "Максимальная длинна сообщения "+kol+" символов", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    ref.push().setValue(messagetext.getText().toString());
                    messagetext.setText("");
                }
            }
        });
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.ccchhhaaattt,R.id.labeled, messages);
        chatT.setAdapter(arrayAdapter);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                messages.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
