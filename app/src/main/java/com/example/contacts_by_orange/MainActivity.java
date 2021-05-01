package com.example.contacts_by_orange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    Button sas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sas = (Button) findViewById(R.id.addcontactbutton);
        mListView = (ListView) findViewById(R.id.contactslist);
        if(ArrayAd.Checedper==0)
        {
            loaD();
        }
        else
        {
            saveD();
            ArrayAd.Checedper=0;
        }
        ArrayAd.arrayAdapter = new ArrayAdapter<String>(this, R.layout.contactslist, R.id.FIO, ArrayAd.contactsFIO);
        mListView.setAdapter(ArrayAd.arrayAdapter);
        sas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAd.FIO = ArrayAd.contactsFIO.get(position);
                ArrayAd.Nomber = ArrayAd.contactsNombers.get(position);
                ArrayAd.UNom = ArrayAd.contactsUN.get(position);
                ArrayAd.nomForChat = ArrayAd.nombersforchat.get(position);
                Intent intent = new Intent(MainActivity.this, InfoContactActivity.class);
                startActivity(intent);
            }
        });

    }
    public void saveD() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Gson gson1 = new Gson();
        Gson gson2 = new Gson();
        Gson gson3 = new Gson();
        String json= gson.toJson(ArrayAd.contactsFIO);
        String json1= gson1.toJson(ArrayAd.contactsNombers);
        String json2= gson2.toJson(ArrayAd.contactsUN);
        String json3= gson3.toJson(ArrayAd.nombersforchat);
        editor.putString("FIO",json);
        editor.putString("Nomber",json1);
        editor.putString("UNom",json2);
        editor.putString("nombersforchat",json3);
        editor.apply();
    }
    public void loaD() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
        Gson gson = new Gson();
        Gson gson1 = new Gson();
        Gson gson2 = new Gson();
        Gson gson3 = new Gson();
        String json= sharedPreferences.getString("FIO", null);
        String json1= sharedPreferences.getString("Nomber", null);
        String json2= sharedPreferences.getString("UNom", null);
        String json3= sharedPreferences.getString("nombersforchat", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayAd.contactsFIO = gson.fromJson(json,type);
        ArrayAd.contactsNombers = gson1.fromJson(json1,type);
        ArrayAd.contactsUN = gson2.fromJson(json2,type);
        ArrayAd.nombersforchat = gson3.fromJson(json3,type);
        if(ArrayAd.contactsFIO == null)
        {
            ArrayAd.contactsFIO = new ArrayList<>();
        }
        if(ArrayAd.contactsNombers == null)
        {
            ArrayAd.contactsNombers = new ArrayList<>();
        }
        if(ArrayAd.contactsUN == null)
        {
            ArrayAd.contactsUN = new ArrayList<>();
        }
        if(ArrayAd.nombersforchat == null)
        {
            ArrayAd.nombersforchat = new ArrayList<>();
        }
    }
}
