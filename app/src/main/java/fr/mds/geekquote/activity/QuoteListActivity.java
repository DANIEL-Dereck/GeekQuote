package fr.mds.geekquote.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import fr.mds.geekquote.R;
import fr.mds.geekquote.adapter.QuoteAdapter;
import fr.mds.geekquote.model.Quote;

public class QuoteListActivity extends Activity implements AdapterView.OnItemClickListener
{
    public static final String TAG = "QuoteListActivity";
    private ArrayList<Quote> quotes = new ArrayList<>();
    private QuoteAdapter quoteAdapter;

    private LinearLayout ll_quote_list_vlayout;
    private Button btn_quote_list_add_quote;
    private EditText et_quote_list_add_quote;
    private ListView lv_list_quote_quotes;

    private void addQuote(String quote) {
        this.quotes.add(new Quote(quote));
        this.quoteAdapter.notifyDataSetChanged();
    }

    private void addQuote(String quote, int rating) {
        this.quotes.add(new Quote(quote, rating));
        this.quoteAdapter.notifyDataSetChanged();
    }

    private void initComponent()
    {
        this.ll_quote_list_vlayout = findViewById(R.id.ll_quote_list_vlayout);
        this.btn_quote_list_add_quote = findViewById(R.id.btn_quote_list_add_quote);
        this.et_quote_list_add_quote = findViewById(R.id.et_quote_list_add_quote);
        this.lv_list_quote_quotes = findViewById(R.id.lv_list_quote_quotes);
    }

    public void onClick(View view)
    {
        if (!this.et_quote_list_add_quote.getText().toString().isEmpty()) {
            addQuote(this.et_quote_list_add_quote.getText().toString());
            this.et_quote_list_add_quote.setText("");
        } else {
            Toast.makeText(this, "Impossible de crée une quote vide.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate");
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.quote_list_activity);
        this.initComponent();
        this.quoteAdapter = new QuoteAdapter(this, quotes);
        lv_list_quote_quotes.setOnItemClickListener(this);
        lv_list_quote_quotes.setAdapter(quoteAdapter);

        for (String quote : getResources().getStringArray(R.array.quotes)) {
            this.addQuote(quote, new Random().nextInt(5));
        }

        this.btn_quote_list_add_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick");
                if (!et_quote_list_add_quote.getText().toString().isEmpty()) {
                    addQuote(et_quote_list_add_quote.getText().toString());
                    et_quote_list_add_quote.setText("");
                } else {
                    Toast.makeText(QuoteListActivity.this, "Impossible de crée une quote vide.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onItemClick(AdapterView<?> theList,
                            View view, int position, long id) {
        String station = (String)theList.getItemAtPosition(position);
        Toast.makeText(this, "item" + position, Toast.LENGTH_SHORT).show();
    }
}
