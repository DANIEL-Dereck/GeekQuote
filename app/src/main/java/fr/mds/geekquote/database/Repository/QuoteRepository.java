package fr.mds.geekquote.database.Repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import fr.mds.geekquote.database.DatabaseManager;
import fr.mds.geekquote.model.Quote;

public class QuoteRepository extends BaseRepository<Quote> {
    @Override
    protected int insert(Quote item) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        int newId = -1;

        values.put(Quote.QuoteContract.COL_STRQUOTE, item.getStrQuote());
        values.put(Quote.QuoteContract.COL_RATING, item.getRating());
        values.put(Quote.QuoteContract.COL_CREATIONDATE, item.getCreationDate().getMillis());

        newId = (int)db.insert(Quote.QuoteContract.TABLE_NAME, null, values);

        DatabaseManager.getInstance().closeDatabase();
        return newId;
    }

    @Override
    protected Quote update(Quote item) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

//        db.update(Quote.QuoteContract.TABLE_NAME, values,"");

        DatabaseManager.getInstance().closeDatabase();
        return null;
    }

    @Override
    protected void delete(Quote item) {
        delete(item.getId());
    }

    @Override
    protected void delete(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query = "DELETE FROM " + Quote.QuoteContract.TABLE_NAME + " WHERE " + Quote.QuoteContract.COL_ID + " = " + id + ";";
        db.execSQL(query);

        DatabaseManager.getInstance().closeDatabase();
    }

    @Override
    protected Quote get(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Quote item = new Quote();
        String query = "SELECT * FROM " + Quote.QuoteContract.TABLE_NAME + " WHERE " + Quote.QuoteContract.COL_ID + " = " + id + " ;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                item = new Quote()
                        .setId(cursor.getInt(cursor.getColumnIndex(Quote.QuoteContract.COL_ID)))
                        .setStrQuote(cursor.getString(cursor.getColumnIndex(Quote.QuoteContract.COL_STRQUOTE)))
                        .setRating(cursor.getFloat(cursor.getColumnIndex(Quote.QuoteContract.COL_RATING)))
                        .setCreationDate(cursor.getLong(cursor.getColumnIndex(Quote.QuoteContract.COL_CREATIONDATE)))
                        ;

            } while (cursor.moveToNext());
        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();
        return item;
    }

    @Override
    protected List<Quote> getAll() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ArrayList<Quote> quotes = new ArrayList<>();
        String query = "SELECT * FROM " + Quote.QuoteContract.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Quote item = new Quote()
                        .setId(cursor.getInt(cursor.getColumnIndex(Quote.QuoteContract.COL_ID)))
                        .setStrQuote(cursor.getString(cursor.getColumnIndex(Quote.QuoteContract.COL_STRQUOTE)))
                        .setRating(cursor.getFloat(cursor.getColumnIndex(Quote.QuoteContract.COL_RATING)))
                        .setCreationDate(cursor.getLong(cursor.getColumnIndex(Quote.QuoteContract.COL_CREATIONDATE)))
                        ;

                quotes.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return quotes;
    }
}
