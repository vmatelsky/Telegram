package org.telegram.techrunch.select_city;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.telegram.messenger.techranch.R;
import org.telegram.techrunch.TechranchConfig;

/**
 * Created by vlad on 5/23/16.
 */
public class CityViewHolder extends ViewHolder {

    TextView mCityField;
    View mNearMeField;

    public CityViewHolder(final View itemView) {
        super(itemView);
        mCityField = (TextView) itemView.findViewById(R.id.city_field);
        mNearMeField = itemView.findViewById(R.id.ic_near_me);
    }

    public void bind(final Context context, final String city, final OnCityClickedListener listener) {
        mCityField.setText(city);

        final TechranchConfig config = new TechranchConfig(context);
        String selectedCity = config.getSelectedCity();
        final boolean isSelected = city.equals(selectedCity);
        itemView.setSelected(isSelected);

        boolean canDisplayNearMe = isSelected && config.isUseNearMeEnabled();
        int nearMeVisibility = canDisplayNearMe ? View.VISIBLE : View.INVISIBLE;
        mNearMeField.setVisibility(nearMeVisibility);

        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                listener.onCitySelected(city);
            }
        });
    }
}
