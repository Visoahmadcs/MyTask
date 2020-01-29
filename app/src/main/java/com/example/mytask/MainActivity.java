package com.example.mytask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private CustomAdapter customAdapter;
    private ArrayList<ContactModel> contactModelArrayList;

    // test
    Button btn_save;
    Button view_data;

    String Cname ="";
    String Cnumber="";
    ContactModel contactModel;
    ContactDb db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test

        db =new  ContactDb(this);

        contactModel = new ContactModel();
        btn_save = findViewById(R.id.save);
        view_data = findViewById(R.id.view);

        btn_save.setOnClickListener(this);
        view_data.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        contactModelArrayList = new ArrayList<>();

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ContactModel contactModel = new ContactModel();
            contactModel.setName(name);
            contactModel.setNumber(phoneNumber);
            contactModelArrayList.add(contactModel);
            Log.d("name>>", name + "  " + phoneNumber);

        }
        phones.close();
        customAdapter = new CustomAdapter(this, contactModelArrayList);
        listView.setAdapter(customAdapter);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                Cname=contactModel.getName();
               Cnumber= contactModel.getNumber();

                db.insertData(Cname,Cnumber);
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
                break;
            case R.id.view:
                Intent intent = new Intent(getApplicationContext(),ViewContent.class);
                startActivity(intent);
        }
    }
}
