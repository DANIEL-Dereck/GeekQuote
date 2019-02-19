package fr.mds.geekquote.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import fr.mds.geekquote.R;
import fr.mds.geekquote.model.Quote;

public class QuoteListActivity extends Activity //implements View.OnClickListener
{
    public static final String TAG = "QuoteListActivity";
    private ArrayList<Quote> quotes = new ArrayList<>();

    private LinearLayout ll_quote_list_vlayout;
    private Button btn_quote_list_add_quote;
    private EditText et_quote_list_add_quote;

    private void addQuote(String quote)
    {
        this.quotes.add(new Quote(quote));

        TextView newQuote = new TextView(this);
        newQuote.setText(quote);

        if (quotes.size() % 2 == 0) {
            newQuote.setBackgroundResource(R.color.colorPair);
        } else {
            newQuote.setBackgroundResource(R.color.colorImpair);
        }

        newQuote.setTextColor(getResources().getColor(android.R.color.white));

        this.ll_quote_list_vlayout.addView(newQuote);
    }

    private void initComponent()
    {
        this.ll_quote_list_vlayout = findViewById(R.id.ll_quote_list_vlayout);
        this.btn_quote_list_add_quote = findViewById(R.id.btn_quote_list_add_quote);
        this.et_quote_list_add_quote = findViewById(R.id.et_quote_list_add_quote);
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

        for (String quote : getResources().getStringArray(R.array.quotes)) {
            this.addQuote(quote);
        }

        this.btn_quote_list_add_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick");
                if (!et_quote_list_add_quote.getText().toString().isEmpty()) {
                    addQuote(et_quote_list_add_quote.getText().toString());
                    et_quote_list_add_quote.setText("");
                } else {
                    Toast.makeText(getBaseContext(), "Impossible de crée une quote vide.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
