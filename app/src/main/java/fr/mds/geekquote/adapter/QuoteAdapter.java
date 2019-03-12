package fr.mds.geekquote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;

import fr.mds.geekquote.R;
import fr.mds.geekquote.model.Quote;

public class QuoteAdapter extends ArrayAdapter<Quote> {

    public QuoteAdapter(Context context, ArrayList<Quote> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Quote quote = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.quote_item, parent, false);
        }

        if (position % 2 == 0){
            convertView.setBackgroundResource(R.color.colorPair);
        } else {
            convertView.setBackgroundResource(R.color.colorImpair);
        }

        TextView tv_quote_item_date = convertView.findViewById(R.id.tv_quote_item_date);
        tv_quote_item_date.setText(quote.getCreationDate().toString(DateTimeFormat.forPattern("dd MMMM yyyy hh:mm:ss")));

        TextView tv_quote_item_quote = convertView.findViewById(R.id.tv_quote_item_quote);
        tv_quote_item_quote.setText(quote.getStrQuote());

        return convertView;
    }
}
