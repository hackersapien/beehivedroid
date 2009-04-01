package org.bandi.android;





import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Details extends Activity {

	private EditText firstname;
	private EditText lastname;
	private EditText age;
	private EditText height;
	private Button endelea;
	private Spinner locationSpinner;
	private Spinner genderSpinner;
	
	public static String fname = "";
	public static String ages = "";
	public static String lname = "";
	public static String he = "";
	public static String gend = "";
	public static String loc = "";
    
	private final String MY_DATABASE_NAME = "myCoolDB_2";
    private final String MY_DATABASE_TABLE = "Users1";

    SQLiteDatabase myDB = null;
    
/** Called when the activity is first created. */

@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		initControls();

	}

	private void initControls() {

		
		
		
		firstname = (EditText)findViewById(R.id.firstname);
		lastname = (EditText)findViewById(R.id.lastname);
		age = (EditText)findViewById(R.id.age);
		height=(EditText)findViewById(R.id.height);
		locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
		genderSpinner = (Spinner) findViewById(R.id.genderSpinner);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.gender_array, R.layout.gender_items);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		genderSpinner.setAdapter(adapter);
		
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				this, R.array.locations_array, R.layout.spinner_location_items);
		adapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		locationSpinner.setAdapter(adapter1);

		
		
        try {
      	  myDB = this.openOrCreateDatabase(MY_DATABASE_NAME, MODE_PRIVATE, null);

             /* Create a Table in the Database. */
             myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                                 + MY_DATABASE_TABLE
                                 + " (LastName VARCHAR, FirstName VARCHAR,"
                                 + " Country VARCHAR, Gender VARCHAR, Height VARCHAR, Age INT(3));");

                 
             
             
                     } catch (Exception ex) {
                                              }

		
		
		
		endelea = (Button)findViewById(R.id.endelea);
			
		endelea.setOnClickListener(new Button.OnClickListener() { 
			
			public void onClick (View v){
				
				    
				    fname = firstname.getText().toString();
		            if (TextUtils.isEmpty(fname)) {
		                // Don't allow an empty name
		                
		            	
		                return;
		            }
		            	ages = age.getText().toString();
		            	lname = lastname.getText().toString();
		            	he = height.getText().toString();
		            	gend = genderSpinner.getSelectedItem().toString();
		            	loc = locationSpinner.getSelectedItem().toString();
		            
		            	String faname = Details.getFname();
		                String laname = Details.getLname();
		                String age = Details.getAge();
		                String heights = Details.getHeight();
		                String location = Details.getLocation();
		                String gender = Details.getGender();
		                
		            	
		            	
		            	/* Add two DataSets to the Table. */
		                myDB.execSQL("INSERT INTO "
		                                    + MY_DATABASE_TABLE
		                                    + " (LastName, FirstName, Country, Age, Gender, Height)"
		                                    + " VALUES ('"+laname+"', '"+faname+"', '"+location+"', '"+age+"', '"+gender+"', '"+heights+"');");
		                
		                
		                if (myDB != null)
		                    myDB.close();
		            	
		            	
		            	
		            	
		            	pic();
			
			}});
		//btnreset.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ reset(); }});

	}

	
	
	
	
	
	private void pic()
    {
        Intent i = new Intent(this, TesteIntent.class);
        startActivity(i);
    }
	
	
	public static String getFname(){
		
		
		return fname;
	}
	
    public static String getLname(){
		
		
		return lname;
	}
	
    public static String getAge(){
		
		
		return ages;
	}

    public static String getHeight(){
		
		
		return he;
	}
    
    public static String getLocation(){
		
		
		return loc;
	}
    
    public static String getGender(){
		
		
		return gend;
	}
    
}

