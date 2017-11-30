package popkovanton.test_appsgeyser.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
//TODO Доделать базу данных
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HistoryWords";
    static final String TABLE_NAME = "response_words";
    static final String INPUT_TEXT = "input_text";
    static final String RESPONSE_TEXT = "response_text";
    private static final String TEXT_TYPE = " TEXT";
    private static final String SQL_CREATE_WORDS_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY, " +
                    INPUT_TEXT + TEXT_TYPE + "," +
                    RESPONSE_TEXT + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_WORDS_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_WORDS_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_WORDS_ENTRIES);
        onCreate(db);
    }
}