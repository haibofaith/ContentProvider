package haibo.com.contentprovider1.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import haibo.com.contentprovider1.dao.DbOpenHelper;

public class BookProvider extends ContentProvider {
    private static final String TAG = "BookProvider";

    private static final String AUTHORITY = "haibo.com.contentprovider1.book.provider";

//    private static final Uri BOOK_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/book");
//    private static final Uri USER_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY,"book",BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY,"user",USER_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    public BookProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.e(TAG,"delete,current Thread:"+Thread.currentThread());
        String table = getTableName(uri);
        int count = mDb.delete(table,selection,selectionArgs);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        Log.e(TAG,"getType,current Thread:"+Thread.currentThread());
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.e(TAG,"insert,current Thread:"+Thread.currentThread());
        String table = getTableName(uri);
        mDb.insert(table,null,values);
        return uri;
    }

    @Override
    public boolean onCreate() {
        Log.e(TAG,"onCreate,current Thread:"+Thread.currentThread());
        mContext = getContext();
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from book");
        mDb.execSQL("delete from user");
        mDb.execSQL("insert into book(name,pages,price,author) values ('安卓开发艺术',20,100,'任玉刚')");
        mDb.execSQL("insert into book(name,pages,price,author) values ('effect java',10,40,'匿名')");
        mDb.execSQL("insert into book(name,pages,price,author) values ('第一行代码',30,50,'郭霖')");
        mDb.execSQL("insert into book(name,pages,price,author) values ('第一行代码',30,50,'郭霖')");
        mDb.execSQL("insert into user(username,password) values ('bobo','000000')");
        mDb.execSQL("insert into user(username,password) values ('hehe','111111')");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.e(TAG,"query,current Thread:"+Thread.currentThread());
        String table = getTableName(uri);
        return mDb.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.e(TAG,"update,current Thread:"+Thread.currentThread());
        String table = getTableName(uri);
        return mDb.update(table,values,selection,selectionArgs);
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (sUriMatcher.match(uri)){
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER;
                break;
        }
        return tableName;
    }

}
