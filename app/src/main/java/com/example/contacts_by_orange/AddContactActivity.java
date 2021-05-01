package com.example.contacts_by_orange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.contacts_by_orange.ArrayAd;
import com.github.pinball83.maskededittext.MaskedEditText;

import java.util.UUID;

public class AddContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact);
        final Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
        final Button add = findViewById(R.id.addcontact), close = findViewById(R.id.closeadd);
        final EditText FIO = findViewById(R.id.FIOadd), uniqnD = findViewById(R.id.uniqnom);
        final MaskedEditText nomberD = findViewById(R.id.nomeradd);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cd=0;
                if (FIO.getText().toString().trim().equals("") || nomberD.getText().toString().trim().equals("") || uniqnD.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show();
                }
                else
                    {
                        for(int i=0;i<ArrayAd.contactsFIO.size();i++)
                        {
                            if ((ArrayAd.contactsFIO.get(i).equals(FIO.getText().toString().trim())
                                    || (ArrayAd.contactsUN.get(i).equals(uniqnD.getText().toString().trim())) ||
                                    (ArrayAd.contactsNombers.get(i).equals(nomberD.getText().toString().trim()))))
                            {
                                cd=1;
                                break;
                            }
                        }
                        if (cd==0)
                        {
                            String uniqnomers = (String) UUID.randomUUID().toString().subSequence(0, 5);
                            ArrayAd.contactsFIO.add(FIO.getText().toString().trim());
                            ArrayAd.contactsNombers.add(nomberD.getText().toString().trim());
                            ArrayAd.contactsUN.add(uniqnD.getText().toString().trim());
                            ArrayAd.nombersforchat.add(uniqnomers);
                            startActivity(intent);
                            ArrayAd.Checedper=1;
                            Toast.makeText(getApplicationContext(), "Контакт добавлен", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Контакт с такимим номером, уникальным кодом или ФИО уже существует", Toast.LENGTH_LONG).show();
                        }

                    }
            }
        });
    }
}
