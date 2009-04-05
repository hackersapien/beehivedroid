package org.bandi.android;

import java.io.File;
import java.io.FilenameFilter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class Images extends Activity
{
	private Uri[] mUrls;
	String[] mFiles=null;


	class ImageFilter implements FilenameFilter
	{
		public boolean accept(File dir, String name)
		{
			return (name.endsWith(".jpg"));
		}
	}
	public void onCreate(Bundle icicle)
	{

		super.onCreate(icicle);
		setTheme(android.R.style.Theme_Light);
		setContentView(R.layout.images);

		File images = new File("/sdcard/");
		File[] imagelist = images.listFiles(new ImageFilter());

		mFiles = new String[imagelist.length];

		for(int i= 0 ; i< imagelist.length; i++)
		{
			mFiles[i] = imagelist[i].getAbsolutePath();
		}
		mUrls = new Uri[mFiles.length];

		for(int i=0; i < mFiles.length; i++)
		{
			mUrls[i] = Uri.parse(mFiles[i]);   
		}	

		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this));
		g.setFadingEdgeLength(40);
				
		

	}
	public class ImageAdapter extends BaseAdapter
	{
		
		int mGalleryItemBackground;
		
		public ImageAdapter(Context c)
		{	
			mContext = c;
			
					
		}


		public int getCount()
		{
			return mUrls.length;
		}

		public Object getItem(int position)
		{
			return position;
		}

		public long getItemId(int position)
		{
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent)
		{
			ImageView i = new ImageView(mContext);

			i.setImageURI(mUrls[position]);
			i.setScaleType(ImageView.ScaleType.FIT_XY);
			i.setLayoutParams(new Gallery.LayoutParams(260, 210));
			
			
			return i;
		}	

		private Context mContext;
		}	
	}
