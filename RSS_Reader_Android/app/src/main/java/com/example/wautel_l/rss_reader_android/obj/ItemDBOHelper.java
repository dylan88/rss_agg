package com.example.wautel_l.rss_reader_android.obj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by wautel_l on 25/02/2018.
 */

public class ItemDBOHelper  extends SQLiteOpenHelper{

    private static String TABLE = "ITEMik";
    private static final String create_table = "create table if not exists item("+
            "ID integer primary key, " +
            "FEED_ID integer, "+
            "NAME string, "+
            "URL string, " +
            "GUID integer, " +
            "DESCRIPTION string, "+
            "CATEGORIE_ID integer, " +
            "READ integer";

    private static final String drop_table = "drop table item";

    public ItemDBOHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_old, int version_new)
    {
        sqLiteDatabase.execSQL(drop_table);
        sqLiteDatabase.execSQL(create_table);
    }
    public ArrayList<Item> getItems(SQLiteDatabase sqLiteDatabase)
    {
        ArrayList<Item> items = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE, null, null, null, null, null, "NAME");
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); ++i)
        {
            items.add(new Item(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7)));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    public void addItem(SQLiteDatabase sqLiteDatabase, Item item) throws  Exception{
        ContentValues cv = new ContentValues();
        cv.put("ID", item.getItem_id());
        cv.put("FEED_ID", item.getFeed_id());
        cv.put("NAME", item.getTitle());
        cv.put("URL", item.getLink());
        cv.put("GUID", item.getGuid());
        cv.put("DESCRIPTION", item.getDescription());
        cv.put("CATEGORIE_ID", item.getCategorie_id());
        cv.put("READ", item.getRead());
        sqLiteDatabase.insert(TABLE, null, cv);
    }

}
