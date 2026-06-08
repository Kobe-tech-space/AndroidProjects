package com.example.course7_server.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.course7_server.database.UserDBHelper;

public class UserInfoProvider extends ContentProvider {
    private UserDBHelper dbHelper;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int USERS = 1;
    private static final int USER = 2;
    static  {
       URI_MATCHER.addURI(UserInfoContent.AUTHORITIES,"user",USERS);
       URI_MATCHER.addURI(UserInfoContent.AUTHORITIES,"user/#",USER);
    }

    public UserInfoProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (URI_MATCHER.match(uri)) {
            case USERS:
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                count = db1.delete(UserDBHelper.TABLE_NAME,selection,selectionArgs);
                db1.close();
                break;
            case USER:
                String id = uri.getLastPathSegment();
                SQLiteDatabase db2 = dbHelper.getWritableDatabase();
                count = db2.delete(UserDBHelper.TABLE_NAME,"_id=?",new String[]{id});
                db2.close();
                break;
        }
        // Implement this to handle requests to delete one or more rows.
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("legion","UserInfoProvider insert");
        if (URI_MATCHER.match(uri) == USERS) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            long rowId = db.insert(UserDBHelper.TABLE_NAME,null,values);
        }
        // TODO: Implement this to handle requests to insert a new row.
       return uri;
    }

    @Override
    public boolean onCreate() {
        Log.d("legion","UserInfoProvider onCreate");
        dbHelper = UserDBHelper.getInstance(getContext());
        // TODO: Implement this to initialize your content provider on startup.
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d("legion","UserInfoProvider query");
        if (URI_MATCHER.match(uri) == USERS) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            return db.query(UserDBHelper.TABLE_NAME,projection,selection,selectionArgs,
                    null,null,null);
        }
        // TODO: Implement this to handle query requests from clients.
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}