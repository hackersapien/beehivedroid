package org.bandi.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.*;

// Created by Chris King, modeled off Google's NotesDbAdapter

public class CameraDbAdapter
{

    public static final String KEY_ROWID    = "_id";
    public static final String KEY_FILE        = "file";
    public static final String KEY_CAPTION    = "caption";
    public static final String KEY_TIME        = "time";
   
    private static final String DATABASE_CREATE =
        "CREATE TABLE IF NOT EXISTS images (_id integer primary key autoincrement, "
            + "file text null, caption text, time integer null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "images";
    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase mDatabase;
    private final Context mContext;

    public CameraDbAdapter(Context context)
    {
        this.mContext = context;
    }
   
    public CameraDbAdapter open() throws SQLException
    {
        
            try
            {
                mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME, DATABASE_VERSION, null);
                mDatabase.execSQL(DATABASE_CREATE);
            }
            catch (Exception e1)
            {
                throw new SQLException("Could not create database");
            }
        
        return this;
    }

    public void close()
    {
        mDatabase.close();
    }
    
    public long createImage(String caption)
    {
        ContentValues initialValues = new ContentValues();
        
        initialValues.put(KEY_CAPTION, caption);
        
        return mDatabase.insert(DATABASE_TABLE, null, initialValues);
    }
	

    //public long createImage(String file, String caption, long time)
    //{
      //  ContentValues initialValues = new ContentValues();
        //initialValues.put(KEY_FILE, file);
        //initialValues.put(KEY_CAPTION, caption);
        //initialValues.put(KEY_TIME, time);
        //return mDatabase.insert(DATABASE_TABLE, null, initialValues);
    //}
	
    public boolean deleteImage(long rowID)
    {
        return mDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowID, null) > 0;
    }

    public Cursor fetchAllImages()
    {
        return mDatabase.query(DATABASE_TABLE, new String[] {
                KEY_ROWID, KEY_FILE, KEY_CAPTION, KEY_TIME}, null, null, null, null, null);
    }

    public Cursor fetchImage(long rowID) throws SQLException
    {
        Cursor result = mDatabase.query(true, DATABASE_TABLE, new String[] {
                KEY_ROWID, KEY_FILE, KEY_CAPTION, KEY_TIME}, KEY_ROWID + "=" + rowID, null, null,
                null, null, null);
        if ((result.getCount() == 0) || !result.isFirst())
        {
            throw new SQLException("No image matching ID: " + rowID);
        }
        return result;
    }

    public boolean updateImage(String file, String caption, long time) {
        ContentValues args = new ContentValues();
        args.put(KEY_FILE, file);
      
        args.put(KEY_TIME, time);
        return mDatabase.update(DATABASE_TABLE, args, KEY_CAPTION + "=" + caption, null) > 0;
    }
    
    //public boolean updateImage(long rowID, String file, String caption, long time) {
      //  ContentValues args = new ContentValues();
        //args.put(KEY_FILE, file);
        //args.put(KEY_CAPTION, caption);
        //args.put(KEY_TIME, time);
     //   return mDatabase.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowID, null) > 0;
    //}
    
    
    
    
    
    
    
}
