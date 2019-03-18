package fr.mds.geekquote.model;

import org.joda.time.DateTime;
import java.io.Serializable;

public class Quote implements Serializable {
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

    @Override
    public String toString() {
        return "Quote{" +
                "strQuote='" + strQuote + '\'' +
                ", rating=" + rating +
                ", creationDate=" + creationDate +
                '}';
    }
}
