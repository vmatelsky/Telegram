package org.telegram.techrunch.my_churches;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import org.telegram.messenger.LocaleController;
import org.telegram.messenger.techranch.R;
import org.telegram.techrunch.TechranchConfig;

/**
 * Created by vlad on 5/20/16.
 */
public class MyChurchesActivityHelper {

    public static View createSelectCityHeader(Context context) {
        final View view = LayoutInflater.from(context).inflate(R.layout.selected_city_header, null);

        TextView label = (TextView) view.findViewById(R.id.selected_city_label);
        label.setText(LocaleController.getString("Techranch_Select_City", R.string.Techranch_Select_City));

        return view;
    }

    public static View createEmptyView(Context context, View.OnClickListener onAddChurchClicked) {
        final View view = LayoutInflater.from(context).inflate(R.layout.no_churches_found, null);

        TextView no_churches_found = (TextView) view.findViewById(R.id.no_churches_found);
        no_churches_found.setText(LocaleController.getString("Techranch_No_Churches", R.string.Techranch_No_Churches));

        Button add_church = (Button) view.findViewById(R.id.add_church);
        add_church.setText(LocaleController.getString("Techranch_Add_Church", R.string.Techranch_Add_Church));

        add_church.setOnClickListener(onAddChurchClicked);

        return view;
    }

    @NonNull
    private static TextView createLabelView(final Context context) {
        TextView label = new TextView(context);
        label.setText(LocaleController.getString("Techranch_Select_City", R.string.Techranch_Select_City));
        label.setTextColor(Color.WHITE);
        label.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        return label;
    }

    @NonNull
    private static TextView createCityView(final Context context) {
        TextView city = new TextView(context);
        TechranchConfig config = new TechranchConfig(context);
        city.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        city.setText(config.getSelectedCity());
        city.setPaintFlags(city.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        city.setTextColor(Color.WHITE);
        return city;
    }
}
