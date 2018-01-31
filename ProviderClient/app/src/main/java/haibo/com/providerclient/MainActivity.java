package haibo.com.providerclient;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("content://haibo.com.contentprovider1.book.provider/book");
        selectCursor(uri);
        Log.e("Main","--------执行前--------");

        Log.e("Main","--------执行后--------");
        selectCursor(uri);
    }

    private void selectCursor(Uri uri){
        Cursor cursor = getContentResolver().query(uri,new String[]{"name","author"},null,null,null);
        while (cursor.moveToNext()){
            Book book = new Book();
            book.setName(cursor.getString(0));
            book.setAuthor(cursor.getString(1));
            Log.e("Main",book.toString());
        }
        cursor.close();
    }

    private void addBook(Uri uri){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","bobo的book");
        contentValues.put("pages",20);
        contentValues.put("price",1000);
        contentValues.put("author","bobo");
        getContentResolver().insert(uri,contentValues);
    }

    private void delete(Uri uri){
        int count = getContentResolver().delete(uri,"name=?",new String[]{"bobo的book"});
        Log.e("Main","成功删除了"+count+"行数据");
    }

    private void update(Uri uri){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","bobo的book");
        contentValues.put("pages",20);
        contentValues.put("price",1000);
        getContentResolver().update(uri,contentValues,"author=?",new String[]{"任玉刚"});
    }
}
