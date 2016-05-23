package org.telegram.techrunch;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * Created by vlad on 5/20/16.
 */
public class DialogsActivityHelper {

    public static View createSelectCityHeader(Context context) {
        LinearLayout content = new LinearLayout(context);
        content.setBackgroundColor(Color.RED);

        TextView label = createLabelView(context);
        TextView city = createCityView(context);

        content.addView(label);
        content.addView(city);

        return content;
    }

    @NonNull
    private static TextView createLabelView(final Context context) {
        TextView label = new TextView(context);
        label.setText("Выбор города");
        label.setTextColor(Color.WHITE);
        label.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        return label;
    }

    @NonNull
    private static TextView createCityView(final Context context) {
        TextView city = new TextView(context);
        TechrunchConfig config = new TechrunchConfig(context);
        city.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        city.setText(config.getSelectedCity());
        city.setPaintFlags(city.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        city.setTextColor(Color.WHITE);
        return city;
    }
}
