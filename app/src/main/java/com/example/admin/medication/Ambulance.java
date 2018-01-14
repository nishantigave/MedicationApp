package com.example.admin.medication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.InputStream;
import java.util.List;

public class Ambulance extends AppCompatActivity {

    private ListView listView1;
    private ItemArrayAdapter1 itemArrayAdapter1;
    String Statev,Cityv;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        listView1 = (ListView) findViewById(R.id.LV5);
        itemArrayAdapter1 = new ItemArrayAdapter1(getApplicationContext(), R.layout.activity_ambulance);

        db = openOrCreateDatabase("dbname",0,null);
        Cursor C = db.rawQuery("select * from Table1;",null);
        //System.out.println(C.getCount());
        C.moveToFirst();
        int i = C.getCount();
        System.out.println();
        while(i!=0){
            Statev = C.getString(0);
            Cityv = C.getString(1);
            i--;
            C.moveToNext();

        }
        System.out.println(Cityv);
        InputStream inputStream1 = getResources().openRawResource(R.raw.hospital_records);
        Parcelable state = listView1.onSaveInstanceState();


        CSVReader1 csv = new CSVReader1(inputStream1);

        List<String[]> scoreList1;
        scoreList1 = csv.read(Cityv);
        //SortedRecords= DB.getCityRecords("Pune");
        //adapter = new CustomAdapter(this, SortedRecords);
        //listView.setAdapter(adapter);
        // listView.deferNotifyDataSetChanged();


        for(String [] scoreData : scoreList1)
        {
            itemArrayAdapter1.add(scoreData);
        }
        listView1.setAdapter(itemArrayAdapter1);
    }
}
