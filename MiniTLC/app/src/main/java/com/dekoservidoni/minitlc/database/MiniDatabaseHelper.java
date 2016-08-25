package com.dekoservidoni.minitlc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dekoservidoni.minitlc.utils.AppConstants;

/**
 * Class responsible to manage the database of the application
 *
 * Created by DeKoServidoni on 2/25/16.
 */
public class MiniDatabaseHelper extends SQLiteOpenHelper {

    /**
     * Constructor
     *
     * @param context
     *          Application context
     */
    public MiniDatabaseHelper(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AppConstants.CREATE_TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion == 1 && newVersion == 2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstants.TABLE_EVENTS);
        }
    }
}
