package org.telegram.techrunch;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.telegram.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class SelectCity extends Activity {

    ListView mCitiesList;
    TextView mSelectedCity;

    TechrunchConfig mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_town);
        setTitle(R.string.techrunch_select_city);

        mCitiesList = (ListView) findViewById(R.id.techrunch_cities_list);
        mSelectedCity = (TextView) findViewById(R.id.techrunch_your_city_name);

        mConfig = new TechrunchConfig(this);
        setCityFromConfig();

        final List<String> your_array_list = new ArrayList<>();
        your_array_list.add("Москва");
        your_array_list.add("Алчевск");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.select_city_item_layout,
                your_array_list);

        mCitiesList.setAdapter(arrayAdapter);

        final TextView v = new TextView(this);
        v.setText("Города");
        v.setTextColor(Color.BLACK);
        mCitiesList.addHeaderView(v);

        mCitiesList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                int withoutHeaderPosition = position - mCitiesList.getHeaderViewsCount();
                if (withoutHeaderPosition >= 0) {
                    final String selectedCity = your_array_list.get(withoutHeaderPosition);
                    mConfig.setSelectedCity(selectedCity);
                    setCityFromConfig();
                }
            }
        });
    }

    private void setCityFromConfig() {
        mSelectedCity.setText(mConfig.getSelectedCity());
    }

}
