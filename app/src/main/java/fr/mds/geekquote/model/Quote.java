package fr.mds.geekquote.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.Serializable;

public class Quote implements Serializable {
    public static class QuoteContract {
        public static final String TABLE_NAME = "quote";

        public static final String COL_ID = "id";
        public static final String COL_STRQUOTE = "quote";
        public static final String COL_RATING = "rating";
        public static final String COL_CREATIONDATE = "created_at";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + COL_STRQUOTE + "TEXT , "
                + COL_RATING + " REAL , "
                + COL_CREATIONDATE + " INTEGER "
                + " );";
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + " ;";
    }

    private int id;
    private String strQuote;
    private float rating;
    private DateTime creationDate;

    public Quote() {

    }

    public Quote(String strQuote) {
        this.strQuote = strQuote;
        this.rating = 0;
        this.creationDate = DateTime.now();
    }

    public Quote(String strQuote, float rating) {
        this.strQuote = strQuote;
        this.rating = rating;
        this.creationDate = DateTime.now();
    }

    public Quote(String strQuote, int rating, DateTime creationDate) {
        this.strQuote = strQuote;
        this.rating = rating;
        this.creationDate = creationDate;
    }

    public int getId() {
        return this.id;
    }

    public Quote setId(int id) {
        this.id = id;
        return this;
    }

    public String getStrQuote() {
        return strQuote;
    }

    public Quote setStrQuote(String strQuote) {
        this.strQuote = strQuote;
        return this;
    }

    public float getRating() {
        return rating;
    }

    public Quote setRating(float rating) {
        this.rating = rating;
        return this;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public Quote setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Quote setCreationDate(Long creationDate) {

        this.creationDate = new DateTime(Long.valueOf(creationDate), DateTimeZone.UTC);
        return this;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "strQuote='" + strQuote + '\'' +
                ", rating=" + rating +
                ", creationDate=" + creationDate +
                '}';
    }
}
