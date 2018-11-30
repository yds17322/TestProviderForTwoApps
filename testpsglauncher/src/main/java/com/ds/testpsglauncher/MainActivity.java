package com.ds.testpsglauncher;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    
    private int mLauncherStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view) {
        Uri eventUri = MyContentProvider.EVENT_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.LAUNCHER_STATUS, 0);
        values.put(DbOpenHelper.NAME, "YYYYY");
        getContentResolver().insert(eventUri, values);
        Log.d("yds", "psglauncher -- add -- ok");
    }

    public void update(View view) {
        Uri eventUri = MyContentProvider.EVENT_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.LAUNCHER_STATUS, mLauncherStatus);
        getContentResolver().update(eventUri, values, DbOpenHelper.NAME + "=?", new String[]{"YYYYY"});
        Log.d("yds", "psglauncher -- update -- ok");
        mLauncherStatus ++;
    }

    public void query(View view) {
        String content = "";
        Uri eventUri = MyContentProvider.EVENT_CONTENT_URI;
        Cursor eventCursor = getContentResolver().query(eventUri, null, null, null, null);
        if (eventCursor != null) {
            while (eventCursor.moveToNext()) {
                int id = eventCursor.getInt(eventCursor.getColumnIndex(DbOpenHelper.ID));
                int launcher_status = eventCursor.getInt(eventCursor.getColumnIndex(DbOpenHelper.LAUNCHER_STATUS));
                String name = eventCursor.getString(eventCursor.getColumnIndex(DbOpenHelper.NAME));
                content += id + ":" + launcher_status + ":" + name + "\n";
            }
            Log.e("yds", "psglauncher - query : \n" + content + "\n");
            eventCursor.close();
        }
    }

    public void delete(View view) {
        Uri eventUri = MyContentProvider.EVENT_CONTENT_URI;
        getContentResolver().delete(eventUri, null, null);
    }
}
