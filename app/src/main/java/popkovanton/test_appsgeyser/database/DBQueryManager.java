package popkovanton.test_appsgeyser.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import popkovanton.test_appsgeyser.data.ModelHistoryElement;

//
// ВЫГРУЗКА ДАННЫХ ИЗ БАЗЫ ДАННЫХ
//
public class DBQueryManager {
    private SQLiteDatabase database;

    public DBQueryManager(SQLiteDatabase database) {
        this.database = database;
    }

    public ArrayList<ModelHistoryElement> getHistoryItems() {
        ArrayList<ModelHistoryElement> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.DATABASE_TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToNext()) {
                do {
                    String text = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TEXT));
                    String language = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_LANGUAGE));
                    ModelHistoryElement modelHistoryElement = new ModelHistoryElement(text, language);
                    arrayList.add(modelHistoryElement);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return arrayList;
    }
}

