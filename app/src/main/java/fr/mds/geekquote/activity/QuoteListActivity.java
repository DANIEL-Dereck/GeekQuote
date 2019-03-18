package fr.mds.geekquote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import fr.mds.geekquote.R;
import fr.mds.geekquote.adapter.QuoteAdapter;
import fr.mds.geekquote.model.Quote;
import fr.mds.geekquote.util.ResultCode;

public class QuoteListActivity extends Activity implements AdapterView.OnItemClickListener {
    public static final int MY_ACTIVITY_CODE = 0x01;
    public static final String QUOTES_STATE = "QUOTES_STATE";
    public static final String TAG = "QuoteListActivity";
    private ArrayList<Quote> quotes = new ArrayList<>();
    private QuoteAdapter quoteAdapter;

    private Button btn_quote_list_add_quote;
    private EditText et_quote_list_add_quote;
    private ListView lv_list_quote_quotes;


    private void initComponent() {
        this.btn_quote_list_add_quote = findViewById(R.id.btn_quote_list_add_quote);
        this.et_quote_list_add_quote = findViewById(R.id.et_quote_list_add_quote);
        this.lv_list_quote_quotes = findViewById(R.id.lv_list_quote_quotes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate");
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.quote_list_activity);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        } else {
            quotes = new ArrayList();

            while (Quote.findAll(Quote.class).hasNext()){
                quotes.add(Quote.findAll(Quote.class).next());
            }
        }

        this.initComponent();
        this.quoteAdapter = new QuoteAdapter(this, quotes);
        lv_list_quote_quotes.setOnItemClickListener(this);
        lv_list_quote_quotes.setAdapter(quoteAdapter);

        this.btn_quote_list_add_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick");
                if (!et_quote_list_add_quote.getText().toString().isEmpty()) {
                    addQuote(et_quote_list_add_quote.getText().toString());
                    et_quote_list_add_quote.setText("");
                } else {
                    Toast.makeText(QuoteListActivity.this, getText(R.string.not_empty_quote), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case QuoteDetailActivity.MY_ACTIVITY_CODE:
                switch (resultCode) {
                    case ResultCode.RESULT_CANCEL:
                        break;
                    case ResultCode.RESULT_OK:
                        if (data != null) {
                            Serializable item = data.getSerializableExtra(QuoteDetailActivity.EXTRA_QUOTE);
                            int position = data.getIntExtra(QuoteDetailActivity.EXTRA_POSITION, -1);

                            if (item instanceof Quote) {
                                Quote newItem = (Quote) item;
                                this.quotes.set(position, newItem);
                                this.quoteAdapter.notifyDataSetChanged();
                            }
                        }
                        break;
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(QUOTES_STATE, this.quotes);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.quotes = (ArrayList<Quote>) savedInstanceState.getSerializable(QUOTES_STATE);
    }

    private void addQuote(String quote) {
        this.quotes.add(new Quote(quote));
        this.quoteAdapter.notifyDataSetChanged();
    }

    private void addQuote(String quote, int rating) {
        this.quotes.add(new Quote(quote, rating));
        this.quoteAdapter.notifyDataSetChanged();
    }

    public void onItemClick(AdapterView<?> theList,
                            View view, int position, long id) {
        Quote item = (Quote)theList.getItemAtPosition(position);

        Intent myIntent = new Intent(QuoteListActivity.this, QuoteDetailActivity.class);
        myIntent.putExtra(QuoteDetailActivity.EXTRA_POSITION, position);
        myIntent.putExtra(QuoteDetailActivity.EXTRA_QUOTE, item);
        startActivityForResult(myIntent, QuoteDetailActivity.MY_ACTIVITY_CODE);
    }
}
