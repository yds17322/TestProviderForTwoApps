package com.ds.testproviderfortwoapps;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String AUTHORITY = "com.ds.testpsglauncher.MyContentProvider"; // 与Provider持有的AndroidManifest保持一致
    public static final Uri EVENT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/event");

    public static final String ID = "id";
    public static final String LAUNCHER_STATUS = "launcher_status";
    public static final String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getContentResolver().registerContentObserver(EVENT_CONTENT_URI, false, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.d("yds", "systemui -- uri --> " + uri);
                String lastPathSegment = uri.getLastPathSegment();
                Log.d("yds", "systemui -- lastPathSegment --> " + lastPathSegment);
                query();

            }
        });
    }

    public void query() {
        String content = "";
        Cursor eventCursor = getContentResolver().query(EVENT_CONTENT_URI, null, null, null, null);
        if (eventCursor != null) {
            while (eventCursor.moveToNext()) {
                int id = eventCursor.getInt(eventCursor.getColumnIndex(ID));
                int launcher_status = eventCursor.getInt(eventCursor.getColumnIndex(LAUNCHER_STATUS));
                String name = eventCursor.getString(eventCursor.getColumnIndex(NAME));
                content += id + ":" + launcher_status + ":" + name + "\n";
            }
            Log.e("yds", "systemui -- query : \n" + content + "\n");
            eventCursor.close();
        }
    }
}
