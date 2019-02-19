package fr.mds.geekquote.model;

import org.joda.time.DateTime;

public class Quote {
    private String strQuote;
    private int rating;
    private DateTime creationDate;

    public Quote() {
    }

    public Quote(String strQuote) {
        this.strQuote = strQuote;
        this.creationDate = DateTime.now();
    }

    public String getStrQuote() {
        return strQuote;
    }

    public Quote setStrQuote(String strQuote) {
        this.strQuote = strQuote;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Quote setRating(int rating) {
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
