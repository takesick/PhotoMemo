package com.example.photomemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
    private final String mTableName = "photo_table";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            /* ここでテーブル作成 */
            db.execSQL(
                    String.format(
                            "create table %s (" +
                                    "uri text primary key not null,"
                                    + "memo text);",
                            mTableName
                    )
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String uri, String memo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uri", uri);
        values.put("memo", memo);
        db.insert(mTableName, null, values);
        db.close();
    }

    public void deleteData(String uri) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(mTableName, "uri=?", new String[]{uri}); db.close();
    }

    public List<PhotoData> readData() {
        List<PhotoData> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(mTableName, null, null, null, null, null, null);
        int uriIdx = c.get...
        int memoIdx = c.get...
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String uriStr = ...;
            Uri uri = Uri.parse(uriStr);
            String memo = ...;
            PhotoData data = new PhotoData(uri, memo); items.add(data);
            c.moveToNext();
        } c.close(); db.close();
        PhotoData を作成 して、List に追加
        return items;
    }


}
