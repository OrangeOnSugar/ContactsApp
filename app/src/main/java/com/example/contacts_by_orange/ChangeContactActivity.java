package com.example.contacts_by_orange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;

import static com.example.contacts_by_orange.ArrayAd.FIO;
import static com.example.contacts_by_orange.ArrayAd.Nomber;
import static com.example.contacts_by_orange.ArrayAd.UNom;
import static com.example.contacts_by_orange.ArrayAd.contactsFIO;

public class ChangeContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changecontact);
        final Intent intent = new Intent(ChangeContactActivity.this, InfoContactActivity.class);
        final Button back = findViewById(R.id.backB), accept = findViewById(R.id.acceptB);
        final EditText FIOC = findViewById(R.id.FIOchangeE), UNC = findViewById(R.id.uniqnomchangeE);
        final MaskedEditText NumberC = findViewById(R.id.nomeupdate);
        String s2 = Nomber.substring(2).replaceAll("[^0-9]", "");
        FIOC.setText(FIO);
        NumberC.setMaskedText(s2);
        UNC.setText(UNom);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cd=0;
                if (FIOC.getText().toString().trim().equals(FIO) && NumberC.getText().toString().trim().equals(Nomber) && UNC.getText().toString().trim().equals(UNom))
                {
                    startActivity(intent);
                }
                else if (FIOC.getText().toString().trim().equals("") || NumberC.getText().toString().trim().equals("") || UNC.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show();
                }
                else
                {
                    for(int i=0;i<ArrayAd.contactsFIO.size();i++)
                    {
                        if ((ArrayAd.contactsFIO.get(i).equals(FIOC.getText().toString().trim()) && !ArrayAd.contactsFIO.get(i).equals(FIO))
                                || (ArrayAd.contactsUN.get(i).equals(UNC.getText().toString().trim()) && !ArrayAd.contactsUN.get(i).equals(UNom)) ||
                                (ArrayAd.contactsNombers.get(i).equals(NumberC.getText().toString().trim()) && !ArrayAd.contactsNombers.get(i).equals(Nomber)))
                        {
                            cd=1;
                            break;
                        }
                    }
                    if (cd==0)
                    {
                        int id = contactsFIO.indexOf(FIO);
                        ArrayAd.contactsFIO.set(id,FIOC.getText().toString());
                        ArrayAd.contactsNombers.set(id,NumberC.getText().toString());
                        ArrayAd.contactsUN.set(id,UNC.getText().toString());
                        FIO = FIOC.getText().toString();
                        Nomber = NumberC.getText().toString();
                        UNom = UNC.getText().toString();
                        startActivity(intent);
                        ArrayAd.Checedper=1;
                        Toast.makeText(getApplicationContext(), "Контакт изменён", Toast.LENGTH_LONG).show();
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
