package org.telegram.techrunch.select_city;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.telegram.techrunch.TechrunchConfig;

/**
 * Created by vlad on 5/23/16.
 */
public class CityViewHolder extends ViewHolder {

    public CityViewHolder(final View itemView) {
        super(itemView);
    }

    public void bind(final Context context, final String city, final OnCityClickedListener listener) {
        ((TextView)itemView).setText(city);

        String selectedCity = new TechrunchConfig(context).getSelectedCity();
        itemView.setSelected(city.equals(selectedCity));

        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                listener.onCitySelected(city);
            }
        });
    }
}
