package org.bandi.android;







import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Androidsample extends Activity {

	
	private Button register;
	private Button view;
	private Button viewg;
	private Button exit;
	private ImageView mIV;
    private Bitmap mBitmap;
    
/** Called when the activity is first created. */

@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
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
		viewg = (Button)findViewById(R.id.viewg);
		
		
		
		register.setOnClickListener(new Button.OnClickListener() {public void onClick (View v){register();}});
		view.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ view(); }});
		viewg.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ viewg(); }});
	exit.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ finish(); }});}
			
	
	
	

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
	
	private void viewg()
    {
        
		Intent i = new Intent(this, Images.class);
		startActivity(i);
    }
	
	
	
	
	
	
}

