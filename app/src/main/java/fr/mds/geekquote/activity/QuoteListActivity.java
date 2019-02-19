package fr.mds.geekquote.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import fr.mds.geekquote.R;
import fr.mds.geekquote.model.Quote;

public class QuoteListActivity extends Activity {

    public static final String TAG = "QuoteListActivity";

    ArrayList<Quote> quotes = new ArrayList<>();

    public void addQuote(String quote)
    {
        this.quotes.add(new Quote(quote));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.quote_list_activity);

        Log.d(TAG, "OnCreate");

        for (String quote : getResources().getStringArray(R.array.quotes)) {
            this.addQuote(quote);
        }

        for (Quote quote : quotes) {
            Log.d(TAG, quote.getStrQuote());
            Toast.makeText(this, quote.getStrQuote(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "OnStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "OnRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy");
    }
}
