package android.bootcamp.androidbootcampday2.storage;

import android.bootcamp.androidbootcampday2.model.Name;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;


public class DataStorage extends SQLiteOpenHelper {

    private static final String DB_NAME = "names.db";
    private static final String TABLE_NAME = "NAMES";
    private static final String NAME_COL = "NAME";
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_COL
            + " TEXT NOT NULL );";
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

    public DataStorage(Context context) {
        super(context, DB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void save(Name name) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COL, name.getName());

        database.insert(TABLE_NAME, null, contentValues);
    }


    public List<Name> getData() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(SELECT_QUERY, null);
        List<Name> names = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(NAME_COL));
                names.add(new Name(name));
                cursor.moveToNext();

            }
        }
        return names;
    }
}
