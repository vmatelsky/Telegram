package org.telegram.techrunch.select_city;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.techrunch.TechrunchConfig;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;

import java.util.Arrays;
import java.util.List;

public class SelectCity extends AppCompatActivity implements OnCityClickedListener {

    RecyclerView mCitiesList;
    TextView mSelectedCity;

    View mClosestToMe;

    TechrunchConfig mConfig;

    private List<String> mCities;

    private CitiesAdapter mCitiesAdapter;
    private CitiesSearchAdapter mCitiesSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_town);

        FrameLayout toolbarPlace = (FrameLayout) findViewById(R.id.toolbar_place);
        ActionBar actionBar = new ActionBar(this);
        toolbarPlace.addView(actionBar);

        actionBar.setTitle(getString(R.string.techrunch_select_city));

        ActionBarMenu menu = actionBar.createMenu();

        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        final ActionBarMenuItem item = menu.addItem(0, R.drawable.ic_ab_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new ActionBarMenuItem.ActionBarMenuItemSearchListener() {
            @Override
            public void onSearchExpand() {
                mClosestToMe.setVisibility(View.GONE);
                mCitiesList.setAdapter(mCitiesSearchAdapter);
            }

            @Override
            public boolean canCollapseSearch() {
                return true;
            }

            @Override
            public void onSearchCollapse() {
                mClosestToMe.setVisibility(View.VISIBLE);
                mCitiesList.setAdapter(mCitiesAdapter);
            }

            @Override
            public void onTextChanged(EditText editText) {
                mCitiesSearchAdapter.getFilter().filter(editText.getText());
            }
        });
        item.getSearchField().setHint(LocaleController.getString("Search", R.string.Search));

        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    onBackPressed();
                }
            }
        });

        mCitiesList = (RecyclerView) findViewById(R.id.techrunch_cities_list);
        mCitiesList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mCitiesList.setLayoutManager(llm);

        mSelectedCity = (TextView) findViewById(R.id.techrunch_your_city_name);

        mConfig = new TechrunchConfig(this);
        setCityFromConfig();

        mCities = Arrays.asList(getResources().getStringArray(R.array.cities_list));
        mCitiesAdapter = new CitiesAdapter(this, mCities, this);
        mCitiesSearchAdapter = new CitiesSearchAdapter(this, mCities, this);
        mCitiesList.setAdapter(mCitiesAdapter);

        mClosestToMe = findViewById(R.id.techrunch_closest_to_me);
        mClosestToMe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(SelectCity.this, "Closest to me clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCityFromConfig() {
        mSelectedCity.setText(mConfig.getSelectedCity());
    }

    @Override
    public void onCitySelected(final String city) {
        mConfig.setSelectedCity(city);
        setCityFromConfig();
        mCitiesList.getAdapter().notifyDataSetChanged();
    }
}
