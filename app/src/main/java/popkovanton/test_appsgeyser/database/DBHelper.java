package popkovanton.test_appsgeyser.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//
// СОЗДАНИЕ БАЗЫ ДАННЫХ
//
public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "appsgeyser_database";
    public static final String DATABASE_TABLE = "appsgeyser_table";
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_TEXT = "text";
    public static final String KEY_LANGUAGE = "language";

    private static final String DATABASE_TABLE_CREATE_SCRIPT =
            "create table " + DATABASE_TABLE + " ("
                    + KEY_ROW_ID + " integer primary key autoincrement, "
                    + KEY_TEXT + " string , "
                    + KEY_LANGUAGE + " string );";

    private DBQueryManager queryManager;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        queryManager = new DBQueryManager(getReadableDatabase());

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    // сохранение айтема в историю
    public void saveHistoryItem(String text, String language) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_TEXT, text);
        contentValues.put(DBHelper.KEY_LANGUAGE, language);
        getWritableDatabase().insert(DBHelper.DATABASE_TABLE, null, contentValues);
    }



    public DBQueryManager query() {
        return queryManager;
    }
}