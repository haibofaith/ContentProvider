package haibo.com.contentprovider1.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by user on 2018/1/31.
 */

public class DbOpenHelper extends SQLiteOpenHelper {
    public  static  final String BOOK = "book";
    public  static  final String USER = "user";

    private static final String CREATE_BOOK = "create table book ("
            +"id integer primary key autoincrement,"+"author text,"
            +"price real,"+"pages integer,"+"name text)";//primary key 主键，autoincrement 自增长

    private static final String CREATE_USER = "create table user ("
            +"id integer primary key autoincrement,"+"username text,"
            +"password text)";//新增一个表

    private Context context;

    public DbOpenHelper(Context mContext) {
        super(mContext,"book_provider.db",null,1);
        this.context = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_USER);//新增一条执行语句
        Toast.makeText(context,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
