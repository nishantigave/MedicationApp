package com.example.admin.medication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    TextView City;
    TextView State;
    Button Home;
    Button Appointment;
    Button Ambulance;
    String Statevariable ;
    String Cityvariable ;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
      // Bundle bundle = getIntent().getExtras();

        /*if (bundle != null) {
            System.out.print("hello");
        Statevariable = bundle.getString("State");
        Cityvariable = bundle.getString("City");
        }*/

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
        //System.out.print("\n\n"+Statevariable+"\n\n\n\n");
        State = (TextView)findViewById(R.id.State);
        City = (TextView)findViewById(R.id.City);
        //System.out.println("\n\n"+Statevariable+"\n\n");
        State.setText(Statevariable);
        City.setText(Cityvariable);
        Home = (Button)findViewById(R.id.Home);
        Appointment = (Button)findViewById(R.id.Appointment);
        Ambulance = (Button)findViewById(R.id.Ambulance);


        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),HomeAssistent.class);
                intent.putExtra("Statevariable",Statevariable);
                intent.putExtra("Cityvariable",Cityvariable);
                startActivity(intent);
            }
        });

        Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Appointment.class);
                intent.putExtra("Statevariable",Statevariable);
                intent.putExtra("Cityvariable",Cityvariable);
                startActivity(intent);
            }
        });

        Ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Ambulance.class);
                intent.putExtra("Statevariable",Statevariable);
                intent.putExtra("Cityvariable",Cityvariable);
                startActivity(intent);
            }
        });

    }
}
