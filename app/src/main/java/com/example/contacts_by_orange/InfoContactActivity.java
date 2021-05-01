package com.example.contacts_by_orange;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.contacts_by_orange.ArrayAd.contactsFIO;
import static com.example.contacts_by_orange.ArrayAd.contactsNombers;
import static com.example.contacts_by_orange.ArrayAd.contactsUN;
import static com.example.contacts_by_orange.ArrayAd.Checedper;
import static com.example.contacts_by_orange.ArrayAd.nombersforchat;
import static com.example.contacts_by_orange.ArrayAd.FIO;
import static com.example.contacts_by_orange.ArrayAd.Nomber;
import static com.example.contacts_by_orange.ArrayAd.UNom;

public class InfoContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactinfo);
         final Intent intent = new Intent(InfoContactActivity.this, MainActivity.class);
         final Button back = findViewById(R.id.closeinfo), changec = findViewById(R.id.changecontact),
                 oc = findViewById(R.id.openchat), dropc = findViewById(R.id.dropcontact);
         final TextView rect = findViewById(R.id.Iniciald);
         final EditText FIO1 = findViewById(R.id.FIOinfo),
                Number1 = findViewById(R.id.nomerinfo),UN = findViewById(R.id.uniqnominfo);
         char s;
         s=FIO.charAt(0);
        rect.setText(""+s);
        FIO1.setText(FIO);
        Number1.setText(Nomber);
        UN.setText(UNom);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        changec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent1 = new Intent(InfoContactActivity.this, ChangeContactActivity.class);
                startActivity(intent1);
            }
        });
        oc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(InfoContactActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        dropc.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        }));
    }
    public void Dialog()
    {
        AlertDialog.Builder did = new AlertDialog.Builder(this);
        did.setMessage("Вы хотите удалить данный контакт?");
        did.setNegativeButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = contactsFIO.indexOf(FIO);
                contactsFIO.remove(id);
                contactsNombers.remove(id);
                contactsUN.remove(id);
                Toast.makeText(getApplicationContext(), "Контакт удалён", Toast.LENGTH_LONG).show();
                Checedper=1;
                final Intent intent = new Intent(InfoContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        did.setPositiveButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        did.create().show();
    }
}