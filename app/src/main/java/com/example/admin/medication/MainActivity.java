package com.example.admin.medication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button Location;
    Button Submit;
    TextView Coordinates;
    EditText State;
    EditText City;
    String sql;
    private LocationManager locationManager;
    private LocationListener locationListener;
    SQLiteDatabase db;
    String s1,c1,city;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location = (Button) findViewById(R.id.location);
        Submit = (Button) findViewById(R.id.submit);
        Coordinates = (TextView) findViewById(R.id.cordinate);
        State = (EditText)findViewById(R.id.EnterState);
        City = (EditText)findViewById(R.id.EnterCity);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                //Coordinates.append("\nLatitude :" + location.getLatitude() + " Longitude : " + location.getLongitude());
                city = hereLocation(location.getLatitude(),location.getLongitude());
                Coordinates.setText(city);
               // Intent a = new Intent(View.getContext(),);
                //sql = "insert into Table1 values(\"\"+s1+\"\\\",\\\"\"+city+\"\");";
                //db.execSQL(sql);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            }, 10);
            return;
        } else {
            configureButton();
        }

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = State.getText().toString();
                c1 = City.getText().toString();
                //System.out.println("\n\n\n"+s1+"\n\n");

                db = openOrCreateDatabase("dbname",0,null);
                db.execSQL("drop table if exists Table1;");
                db.execSQL("create table Table1(State varchar(50),City varchar(50))");
                    sql = "insert into Table1 values(\""+s1+"\",\""+c1+"\");";
                db.execSQL(sql);

                Intent intent = new Intent(view.getContext(),Menu.class);
                // intent.putExtra("State",s1);
                //intent.putExtra("City",c1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {
        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates("gps", 10000, 5, locationListener);
            }
        });


    }

    public String hereLocation(double lat,double lon){
        String City="";
        Geocoder geocoder=new Geocoder(MainActivity.this, Locale.getDefault());
        List<Address> addressList;
        try
        {
            addressList=geocoder.getFromLocation(lat,lon,1);
            if (addressList.size()>0)
            {
                City=addressList.get(0).getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return City;
    }

    /* Submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            s1 = State.getText().toString();
            c1 = City.getText().toString();
            //System.out.println("\n\n\n"+s1+"\n\n");

            db = openOrCreateDatabase("dbname",0,null);
            db.execSQL("drop table if exists Table1;");
            db.execSQL("create table Table1(State varchar(50),City varchar(50))");
            sql = "insert into Table1 values(\""+s1+"\",\""+c1+"\");";
            db.execSQL(sql);

            Intent intent = new Intent(view.getContext(),Menu.class);
            // intent.putExtra("State",s1);
            //intent.putExtra("City",c1);
            startActivity(intent);
        }
    });*/


}

