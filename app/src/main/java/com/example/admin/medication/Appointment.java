package com.example.admin.medication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.InputStream;
import java.util.List;

public class Appointment extends AppCompatActivity {

    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    String Statevariable,Cityvariable;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        listView = (ListView) findViewById(R.id.LV2);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.activity_appointment);

        db = openOrCreateDatabase("dbname",0,null);
        Cursor C = db.rawQuery("select * from Table1;",null);
        System.out.println(C.getCount());
        C.moveToFirst();
        int i = C.getCount();
        System.out.println();
        while(i!=0){
            Statevariable = C.getString(0);
            Cityvariable = C.getString(1);
            i--;
            C.moveToNext();

        }
        InputStream inputStream = getResources().openRawResource(R.raw.dispensaries_with_geocode17_sep_2015);
        Parcelable state = listView.onSaveInstanceState();


        CSVReader csv = new CSVReader(inputStream);

        List<String[]> scoreList = csv.read(Cityvariable);
        //SortedRecords= DB.getCityRecords("Pune");
        //adapter = new CustomAdapter(this, SortedRecords);
        //listView.setAdapter(adapter);
        // listView.deferNotifyDataSetChanged();


        for(String [] scoreData : scoreList)
        {
            itemArrayAdapter.add(scoreData);
        }
        listView.setAdapter(itemArrayAdapter);


    }
}
