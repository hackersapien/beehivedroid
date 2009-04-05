package org.bandi.android;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;


public class Details extends Activity {

	private EditText firstname;
	private EditText lastname;
	private EditText age;
	private EditText height;
	private Button endelea;
	private  Button back;
	private Spinner locationSpinner;
	private Spinner genderSpinner;
	
	public static String fname = "";
	public static String ages = "";
	public static String lname = "";
	public static String he = "";
	public static String gend = "";
	public static String loc = "";
	public static String caption = "";
    
	private final String MY_DATABASE_NAME = "myCoolDB_2";
    private final String MY_DATABASE_TABLE = "Users1";

    CameraDbAdapter image = new CameraDbAdapter(this);

    SQLiteDatabase myDB = null;
    
/** Called when the activity is first created. */

@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Light);
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
    
		
		endelea = (Button)findViewById(R.id.endelea);
		
        try {
      	  myDB = this.openOrCreateDatabase(MY_DATABASE_NAME, MODE_PRIVATE, null);

             /* Create a Table in the Database. */
             myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                                 + MY_DATABASE_TABLE
                                 + " (LastName VARCHAR, FirstName VARCHAR,"
                                 + " Country VARCHAR, Gender VARCHAR, Height VARCHAR, Age INT(3));");

                 
             
             
                     } catch (Exception ex) {
                                              }
        
		
		
		
		    
		endelea.setOnClickListener(new Button.OnClickListener() { 
			
			

			public void onClick (View v){
				
				    
				    fname = firstname.getText().toString();
		            if (TextUtils.isEmpty(fname)) {
		                // Don't allow an empty name
		            	String message = "Please Enter First Name";
		            	Toast.makeText(Details.this, message, Toast.LENGTH_SHORT).show();
		                
		            	
		                return;
		            }
		            	else {
		            	
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
		                
		         
		                //TODO: CREATE AND STORE CAPTION
		                //caption = "Name:" + faname + " " + laname + "\n" + "Age:" + age + "\n" + "Country:" + location + "\n" + "Gender:" + gender;
		                caption = "Name:" + faname + " " + laname;
		                
		                
		                
		                if (myDB != null)
		                    myDB.close();
		            	
		            	
		            	image.open();
		            	image.createImage(caption);
		            	
		            	String saved = "Record Saved, Please make sure you take a Picture";
		            	Toast.makeText(Details.this, saved, Toast.LENGTH_LONG).show();
		                
		            	pic();}
			
			}});}
		//back.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ back(); }});

	

	
	
	private void back(){
		
		Intent i = new Intent(this, Androidsample.class);
        startActivity(i);	
	}
	
	
	private void pic()
    {
        
        Intent i = new Intent(this, Camera.class);
        startActivity(i);
    }
	
	public static String getCaption(){
		
		
		return caption;
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

