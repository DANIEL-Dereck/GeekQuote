package fr.mds.geekquote.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import fr.mds.geekquote.model.Quote;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String LOGTAG = this.getClass().toString();

    /* DB VALUES */
    private static final String DATABASE_NAME = "quote.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        Log.d(LOGTAG, "Open DB");
        super.onOpen(db);
        if (!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOGTAG, "onCreate");
        db.execSQL(Quote.QuoteContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(LOGTAG, "onUpgrade");
        db.execSQL(Quote.QuoteContract.DROP_TABLE);
        onCreate(db);
    }
}
