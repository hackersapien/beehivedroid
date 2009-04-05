package org.bandi.android;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Editor extends Activity {
   
    private TextView mCaption;
    private Cursor mCursor;
    private CameraDbAdapter mAdapter;
   
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setTheme(android.R.style.Theme_Light);
        setContentView(R.layout.editor);
       
        try {
        final long id = getIntent().getLongExtra(CameraDbAdapter.KEY_ROWID, -1);
       
        mCaption = (TextView)findViewById(R.id.editorCaption);
       
        mAdapter = new CameraDbAdapter(this);
        mAdapter.open();
        mCursor = mAdapter.fetchImage(id);
        startManagingCursor(mCursor);
       
        ImageView image = (ImageView)findViewById(R.id.editorImage);
        image.setImageBitmap( BitmapFactory.decodeFile(getFilesDir() + "/" +
                mCursor.getString(mCursor.getColumnIndex(CameraDbAdapter.KEY_FILE))));
       
        mCaption.setText(mCursor.getColumnIndex(CameraDbAdapter.KEY_CAPTION));
       
        }
        catch (Exception e){
        
        	        			                
        }
        
        
        Button upload = (Button)findViewById(R.id.editorUpload);
        upload.setOnClickListener(new View.OnClickListener () {
            public void onClick(View v)
            {
            	
            	finish();
                Intent i = new Intent(Editor.this, Androidsample.class);
                
                startActivity(i);
            }
        });
   }
   
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mAdapter.close();
    }
   
    
}
