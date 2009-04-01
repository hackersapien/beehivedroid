package org.bandi.android;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class DataBaseWork extends ListActivity {

     private final String MY_DATABASE_NAME = "myCoolDB_2";
     private final String MY_DATABASE_TABLE = "Users1";

     /** Called when the activity is first created. */
     @Override
     public void onCreate(Bundle icicle) {
          super.onCreate(icicle);
          
          EditText et = new EditText(this);
          et.setSelection(et.getText().length());
          /* Will hold the 'Output' we want to display at the end. */
          ArrayList<String> results = new ArrayList<String>();

          String fname = Details.getFname();
          String lname = Details.getLname();
          String age = Details.getAge();
          String height = Details.getHeight();
          String location = Details.getLocation();
          String gender = Details.getGender();
          
          
          SQLiteDatabase myDB = null;
          try {
        	  myDB = this.openOrCreateDatabase(MY_DATABASE_NAME, MODE_PRIVATE, null);

               /* Create a Table in the Database. */
               myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                                   + MY_DATABASE_TABLE
                                   + " (LastName VARCHAR, FirstName VARCHAR,"
                                   + " Country VARCHAR, Gender VARCHAR, Height VARCHAR, Age INT(3));");

               /* Add two DataSets to the Table. */
               myDB.execSQL("INSERT INTO "
                                   + MY_DATABASE_TABLE
                                   + " (LastName, FirstName, Country, Age, Gender, Height)"
                                   + " VALUES ('"+lname+"', '"+fname+"', '"+location+"', '"+age+"', '"+gender+"', '"+height+"');");
               
               
                   
               
               
               /* Query for some results with Selection and Projection. */
               Cursor c = myDB.rawQuery("SELECT FirstName,LastName,Age,Country,Gender" +
                                        " FROM " + MY_DATABASE_TABLE,
                                        null);
               
               /* Get the indices of the Columns we will need */
               int firstNameColumn = c.getColumnIndex("FirstName");
               int ageColumn = c.getColumnIndex("Age");
               int locaColumn = c.getColumnIndex("Country");
               int lnameColumn = c.getColumnIndex("LastName");
               int genderColumn = c.getColumnIndex("Gender");
               /* Check if our result was valid. */
               c.moveToFirst();
               if (c != null) {
                    /* Check if at least one Result was returned. */
                    if (c.isFirst()) {
                         int i = 0;
                         /* Loop through all Results */
                         do {
                              i++;
                              /* Retrieve the values of the Entry
                               * the Cursor is pointing to. */
                              String firstName = c.getString(firstNameColumn);
                              int age1 = c.getInt(ageColumn);
                              String lastname = c.getString(lnameColumn);
                              String locs = c.getString(locaColumn);
                              String gen = c.getString(genderColumn);
                              
                              /* We can also receive the Name
                               * of a Column by its Index.
                               * Makes no sense, as we already
                               * know the Name, but just to show we can Wink */
                              String ageColumName = c.getColumnName(ageColumn);
                              
                              if (!TextUtils.isEmpty(firstName)) 
                              /* Add current Entry to results. */
                              results.add("" + i + ": " + firstName + " " + lastname + "\n"
                                             + "    " + ageColumName + ": " + age1 + "\n" + "    " + "Location:" + locs + "\n" + "    " + "Gender:" + gen );
         
                              
                              } while(c.moveToNext());
                    
                    }
               }

          } finally {
               if (myDB != null)
                    myDB.close();
          }

          this.setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, results));
     }

     
}
