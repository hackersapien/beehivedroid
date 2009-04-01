package org.bandi.android;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class TesteIntent extends Activity {

     private static final String CATEGORIA = "livro";

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          try {
               Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
               startActivityForResult(i, 5);
          } catch (Exception e) {
               Log.e(CATEGORIA, e.getMessage());
          }
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          super.onActivityResult(requestCode, resultCode, data);

          Bundle extras = data.getExtras();

          Bitmap b = (Bitmap) extras.get("data");
          System.out.println(b.getWidth() + ", " + b.getHeight());

          ImageView img = new ImageView(this);
          img.setImageBitmap(b);
          setContentView(img);

          try {
        	   String filename = Details.getFname() + ".jpg";
        		   
               FileOutputStream out = openFileOutput(filename, MODE_APPEND);
               BufferedOutputStream bos = new BufferedOutputStream(out);
               b.compress(Bitmap.CompressFormat.JPEG, 100, out);
               bos.flush();
               bos.close();
               Intent i = new Intent(this, Androidsample.class);
               startActivity(i);

          } catch (IOException ex) {
               Log.e(CATEGORIA, ex.getMessage());
          }
     }

}
