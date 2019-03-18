package fr.mds.geekquote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import org.joda.time.format.DateTimeFormat;
import fr.mds.geekquote.R;
import fr.mds.geekquote.model.Quote;
import fr.mds.geekquote.util.ResultCode;

public class QuoteDetailActivity extends Activity {
    public static final int MY_ACTIVITY_CODE = 0x02;
    public static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    public static final String EXTRA_POSITION = "EXTRA_POSITION";
    public static final String EXTRA_QUOTE_ID = "EXTRA_QUOTE_ID";

    private EditText et_quote_detail_quote;
    private TextView tv_quote_detail_date;
    private RatingBar rb_quote_detail_rating;
    private Button btn_quote_detail_ok;
    private Button btn_quote_detail_cancel;
    private Button btn_quote_detail_del;

    private Quote item;
    private int position;

    private void initItems()
    {
        this.et_quote_detail_quote = findViewById(R.id.et_quote_detail_quote);
        this.tv_quote_detail_date = findViewById(R.id.tv_quote_detail_date);
        this.rb_quote_detail_rating = findViewById(R.id.rb_quote_detail_rating);
        this.btn_quote_detail_ok = findViewById(R.id.btn_quote_detail_ok);
        this.btn_quote_detail_cancel = findViewById(R.id.btn_quote_detail_cancel);
        this.btn_quote_detail_del = findViewById(R.id.btn_quote_detail_del);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_detail_activity);
        this.initItems();

        final Intent myIntent = this.getIntent();

        if (myIntent.getExtras() != null && myIntent.getSerializableExtra("EXTRA_QUOTE") instanceof Quote) {
            this.item = (Quote) myIntent.getSerializableExtra("EXTRA_QUOTE");
            this.et_quote_detail_quote.setText(item.getStrQuote());
            this.tv_quote_detail_date.setText(item.getCreationDate().toString(DateTimeFormat.forPattern("dd MMMM yyyy hh:mm:ss")));
            this.rb_quote_detail_rating.setRating(item.getRating());

            this.position = myIntent.getIntExtra(QuoteDetailActivity.EXTRA_POSITION, -1);
        }

        this.btn_quote_detail_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(ResultCode.RESULT_CANCEL);
                finish();
            }
        });

        this.btn_quote_detail_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.setStrQuote(et_quote_detail_quote.getText().toString());
                item.setRating(rb_quote_detail_rating.getRating());

                myIntent.putExtra(QuoteDetailActivity.EXTRA_QUOTE, item);
                myIntent.putExtra(QuoteDetailActivity.EXTRA_POSITION, position);

                setResult(ResultCode.RESULT_OK, myIntent);
                finish();
            }
        });

        this.btn_quote_detail_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent.putExtra(QuoteDetailActivity.EXTRA_QUOTE_ID, item.getId());
                myIntent.putExtra(QuoteDetailActivity.EXTRA_POSITION, position);

                setResult(ResultCode.RESULT_DELETE, myIntent);
                finish();
            }
        });
    }
}
