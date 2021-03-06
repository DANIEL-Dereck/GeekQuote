package fr.mds.geekquote.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import fr.mds.geekquote.database.Repository.QuoteRepository;
import fr.mds.geekquote.model.Quote;
import fr.mds.geekquote.util.ResultCode;

public class QuoteListActivity extends Activity implements AdapterView.OnItemClickListener {
    public static final int MY_ACTIVITY_CODE = 0x01;
    public static final String QUOTES_STATE = "QUOTES_STATE";
    public static final String TAG = "QuoteListActivity";
    private ArrayList<Quote> quotes = new ArrayList<>();
    private QuoteAdapter quoteAdapter;

    private SharedPreferences sharedPreferences;
    private QuoteRepository quoteRepository;

    private Button btn_quote_list_add_quote;
    private EditText et_quote_list_add_quote;
    private ListView lv_list_quote_quotes;

    // Init all component used in this activity.
    private void initComponent() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        quoteRepository = new QuoteRepository();

        this.btn_quote_list_add_quote = findViewById(R.id.btn_quote_list_add_quote);
        this.et_quote_list_add_quote = findViewById(R.id.et_quote_list_add_quote);
        this.lv_list_quote_quotes = findViewById(R.id.lv_list_quote_quotes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate");
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.quote_list_activity);

        this.initComponent();

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        } else {
            this.quotes.clear();
            this.quotes.addAll(this.quoteRepository.getAll());
        }


        this.quoteAdapter = new QuoteAdapter(this, quotes);
        lv_list_quote_quotes.setOnItemClickListener(this);
        lv_list_quote_quotes.setAdapter(quoteAdapter);

        // Event onClick.
        this.btn_quote_list_add_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick");
                if (!et_quote_list_add_quote.getText().toString().isEmpty()) {
                    addQuote(et_quote_list_add_quote.getText().toString());

                    // Add last quote in sharePreference.
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().putString("LAST_QUOTE", et_quote_list_add_quote.getText().toString()).commit();
                    }

                    et_quote_list_add_quote.setText("");
                } else {
                    Toast.makeText(QuoteListActivity.this, getText(R.string.not_empty_quote), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String lastQuote = sharedPreferences.getString("LAST_QUOTE","");

        if (lastQuote != null && !lastQuote.isEmpty()) {
            Toast.makeText(this, lastQuote, Toast.LENGTH_SHORT).show();
        }
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

                                this.quoteRepository.update(newItem);
                                this.quotes.set(position, newItem);
                                this.quoteAdapter.notifyDataSetChanged();
                            }
                        }
                        break;
                    case ResultCode.RESULT_DELETE:
                        int position = data.getIntExtra(QuoteDetailActivity.EXTRA_POSITION, -1);
                        int idToDelete = data.getIntExtra(QuoteDetailActivity.EXTRA_QUOTE_ID, -1);

                        if (idToDelete >= 1) {
                            this.quotes.remove(position);
                            this.quoteRepository.delete(idToDelete);
                        }
                        this.quoteAdapter.notifyDataSetChanged();
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
        if (savedInstanceState.getSerializable(QUOTES_STATE) instanceof ArrayList) {
//            this.quotes = (ArrayList<Quote>) savedInstanceState.getSerializable(QUOTES_STATE);
            this.quotes.clear();
            this.quotes.addAll(this.quoteRepository.getAll());
        }
    }

    // Add quote.
    private void addQuote(String quote) {
        Quote item = new Quote(quote);
        this.quoteRepository.insert(item);
        this.quotes.add(item);

        if (this.quoteAdapter != null) {
            this.quoteAdapter.notifyDataSetChanged();
        }
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
