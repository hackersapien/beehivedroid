package org.bandi.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Album extends ListActivity
{

    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
    }
   
    @Override
    public void onResume()
    {
        super.onResume();
        CameraDbAdapter adapter = new CameraDbAdapter(this);
        adapter.open();
        SimpleCursorAdapter labels = new SimpleCursorAdapter(this, R.layout.camera_item,
                adapter.fetchAllImages(),
                new String[]{CameraDbAdapter.KEY_CAPTION},
                new int[]{R.id.albumItemCaption});
        setListAdapter(labels);
    }
   
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent i = new Intent(this, Editor.class);
        i.putExtra(CameraDbAdapter.KEY_ROWID, id);
        startActivity(i);
    }
}
