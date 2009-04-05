package org.bandi.android;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Camera extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.camera);
       
        Button advance = (Button)findViewById(R.id.cameraGallery);
        advance.setOnClickListener(new View.OnClickListener ()
        {
            public void onClick(View v)
            {
                advance();
            }
        });
       
        Button click = (Button)findViewById(R.id.cameraInstructions);
        click.setOnClickListener(new View.OnClickListener ()
        {
            public void onClick(View v)
            {
         try {       
            	Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(i, 5);
         }
         catch (Exception e){}
                
            }
        });}
            	
            	
            	
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
             super.onActivityResult(requestCode, resultCode, data);

             Bundle extras = data.getExtras();

             Bitmap b = (Bitmap) extras.get("data");
             System.out.println(b.getWidth() + ", " + b.getHeight());

             String filename = Long.toString(System.currentTimeMillis()) + ".jpg";
             try
             {
                 FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
                 b.compress(CompressFormat.JPEG, 100, fos);
                 fos.flush();
                 fos.close();
                
                 CameraDbAdapter adapter = new CameraDbAdapter(Camera.this);
                 adapter.open();
                 adapter.updateImage(filename, Details.getCaption(), System.currentTimeMillis());                   
                 adapter.close();
                 advance();

             }
             catch(IOException e)
             {
                 Log.e("Camera", "Saving failed.", e);
             }               

        }
	
            	
    private void advance()
    {
        Intent i = new Intent(this, Androidsample.class);
        startActivity(i);
    }
}
