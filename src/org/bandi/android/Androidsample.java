package org.bandi.android;







import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class Androidsample extends Activity {

	
	private Button register;
	private Button view;
	private Button exit;
	private ImageView mIV;
    private Bitmap mBitmap;
    
/** Called when the activity is first created. */

@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Light);
		setContentView(R.layout.main);
		initControls();

	}

	private void initControls() {
        
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);  
        
        
        mIV = (ImageView)findViewById(R.id.picview);
        mIV.setImageBitmap(mBitmap); 
        mIV.invalidate();
	
		register = (Button)findViewById(R.id.register);
		exit = (Button)findViewById(R.id.exit);
		view = (Button)findViewById(R.id.view);	
		
		
		
		
		register.setOnClickListener(new Button.OnClickListener() {public void onClick (View v){register();}});
		view.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ view(); }});
	
	exit.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){
		
		String exit = "Thank You for using our Application";
    	Toast.makeText(Androidsample.this, exit, Toast.LENGTH_LONG).show();
        
		finish(); }});}
			
	
	
	

	private void register()
    {
		
        Intent i = new Intent(this, Details.class);
        startActivity(i);
    }
	
	private void view()
    {
        
		Intent i = new Intent(this, DataBaseWork.class);
		startActivity(i);
    }
	
	
	
	
	
	
	
}

