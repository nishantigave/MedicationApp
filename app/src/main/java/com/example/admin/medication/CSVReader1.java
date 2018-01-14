package com.example.admin.medication;

import android.content.ContentValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/01/2018.
 */

public class CSVReader1 {
    InputStream inputStream1;
    String s1;

    public CSVReader1 (InputStream is )
    {
        this.inputStream1 = is;

    }

    public List<String[]> read(String District) {
        List<String[]> resultList1 = new ArrayList<String[]>();
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1));

        //db =db.getWritableDatabase(); /* THE ERROR SHOWN HERE : " " */

        String csvLine = "";

        try {
            reader1.readLine();
            while((csvLine = reader1.readLine()) != null) {
                String[] row = csvLine.split(",");

               /* if (row.length != 11) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }*/
                //s1 = row[8].trim().toString();

                //if(District.equalsIgnoreCase(s1)) {

                    resultList1.add(row);
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return resultList1;
    }
}
