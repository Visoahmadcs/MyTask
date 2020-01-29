package com.example.mytask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewContent extends AppCompatActivity {


    ListView list;
    ContactDb db;
    ArrayList<String> arr1;
    ArrayList <String> arr2;
    ArrayList <String> arr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_content);



        list = findViewById(R.id.view_data);
        db = new ContactDb(getApplicationContext());
        db.getReadableDatabase();
        arr1 = db.extractName();
        arr2 = db.extractNumber();

        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

    }


    private class MyAdapter extends BaseAdapter {

        TextView c_name,c_number;
        LayoutInflater inflater;

        public MyAdapter() {
            inflater =(LayoutInflater) ViewContent.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        }

        @Override
        public int getCount() {
            return arr1.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null){
                view = inflater.inflate(R.layout.update,null);
                //id = (TextView) view.findViewById(R.id.txtId);
                c_name =  view.findViewById(R.id.txtName);
                c_number =  view.findViewById(R.id.txtPassword);

                //id.setText(" ID : "+arr1.get(i)+". ");
                c_name.setText(arr1.get(position));
                c_number.setText(arr2.get(position));
            }
            return view;
        }
    }
}
