package com.example.admin.medication;

import android.content.ContentValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/01/2018.
 */

public class CSVReader {
    InputStream inputStream;


    public CSVReader (InputStream is )
    {
        this.inputStream = is;

    }

    public List<String[]> read(String Cityvariable) {
        List<String[]> resultList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        //db =db.getWritableDatabase(); /* THE ERROR SHOWN HERE : " " */

        String csvLine = "";

        try {
            reader.readLine();
            while((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");

               /* if (row.length != 11) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }*/
               String s1 = row[6].trim().toString();

               if(Cityvariable.equalsIgnoreCase(s1)) {
                   ContentValues cv = new ContentValues(11);
                   cv.put("Hospitalname", row[1].trim());
                   cv.put("Category", row[2].trim());
                   cv.put("CareType", row[3].trim());
                   cv.put("Address", row[4].trim());
                   cv.put("State", row[5].trim());
                   cv.put("District", s1);
                   cv.put("Telephone", row[9].trim());
                   cv.put("AreaPinCode", row[8].trim());
                   cv.put("NoofBeds", row[10].trim());
                   cv.put("Mobilenumber", row[11].trim());
                   cv.put("Websitelink", row[12].trim());
                   cv.put("Latitude", row[13].trim());
                   cv.put("Longitude", row[14].trim());
                   cv.put("Facilities", row[15].trim());
                   cv.put("Ayush", row[16].trim());
                   //db.insert("TABLE", null, cv);
                   resultList.add(row);
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return resultList;
    }
}
