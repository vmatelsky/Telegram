package org.telegram.techrunch.select_city;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.techranch.R;
import org.telegram.techrunch.TechranchConfig;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

import java.util.Arrays;
import java.util.List;

public class SelectCity extends BaseFragment implements OnCityClickedListener {

    private RecyclerListView listView;
    private LinearLayoutManager layoutManager;

    TextView mSelectedCity;

    Button mClosestToMe;

    TechranchConfig mConfig;

    private List<String> mCities;

    private CitiesAdapter mCitiesAdapter;
    private CitiesSearchAdapter mCitiesSearchAdapter;

    @Override
    public View createView(final Context context) {
        actionBar.setTitle(LocaleController.getString("techrunch_select_city", R.string.techrunch_select_city));

        ActionBarMenu menu = actionBar.createMenu();

        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        final ActionBarMenuItem item = menu.addItem(0, R.drawable.ic_ab_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new ActionBarMenuItem.ActionBarMenuItemSearchListener() {
            @Override
            public void onSearchExpand() {
                mClosestToMe.setVisibility(View.GONE);
                listView.setAdapter(mCitiesSearchAdapter);
            }

            @Override
            public boolean canCollapseSearch() {
                return true;
            }

            @Override
            public void onSearchCollapse() {
                mClosestToMe.setVisibility(View.VISIBLE);
                listView.setAdapter(mCitiesAdapter);
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
                    finishFragment();
                }
            }
        });

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        fragmentView = layout;

        listView = new RecyclerListView(context);
        listView.setVerticalScrollBarEnabled(true);
        listView.setItemAnimator(null);
        listView.setLayoutAnimation(null);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);
        if (Build.VERSION.SDK_INT >= 11) {
            listView.setVerticalScrollbarPosition(LocaleController.isRTL ? ListView.SCROLLBAR_POSITION_LEFT : ListView.SCROLLBAR_POSITION_RIGHT);
        }

        LinearLayout subheader = new LinearLayout(context);
        subheader.setOrientation(LinearLayout.VERTICAL);
        subheader.setBackgroundColor(context.getResources().getColor(R.color.techrunch_primary_color));

        mSelectedCity = new TextView(context);
        mSelectedCity.setGravity(Gravity.CENTER_HORIZONTAL);
        mSelectedCity.setTextColor(Color.WHITE);
        mSelectedCity.setTextSize(context.getResources().getDimension(R.dimen.techranch_selected_city_text_cize));
        mConfig = new TechranchConfig(context);
        setCityFromConfig();

        TextView selectedCityHeader = new TextView(context);
        selectedCityHeader.setGravity(Gravity.CENTER_HORIZONTAL);
        selectedCityHeader.setTextColor(Color.WHITE);
        selectedCityHeader.setText(LocaleController.getString("techrunch_your_city", R.string.techrunch_your_city));

        subheader.addView(selectedCityHeader, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));
        subheader.addView(mSelectedCity, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));

        layout.addView(subheader, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));

        FrameLayout container = new FrameLayout(context);
        container.addView(listView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));
        layout.addView(container, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));

        mCities = Arrays.asList(context.getResources().getStringArray(R.array.cities_list));
        mCitiesAdapter = new CitiesAdapter(context, mCities, this);
        mCitiesSearchAdapter = new CitiesSearchAdapter(context, mCities, this);
        listView.setAdapter(mCitiesAdapter);

        mClosestToMe = new Button(context);
        mClosestToMe.setText(LocaleController.getString("techrunch_closest_to_me", R.string.techrunch_closest_to_me));
        mClosestToMe.setBackgroundColor(context.getResources().getColor(R.color.techrunch_primary_color));
        mClosestToMe.setTextColor(Color.WHITE);

        mClosestToMe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                final TechranchConfig config = new TechranchConfig(context);

                config.setUseNearMe(!config.isUseNearMeEnabled());

                listView.getAdapter().notifyDataSetChanged();
            }
        });


        layout.addView(mClosestToMe, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, AndroidUtilities.dp(20)));

        return fragmentView;
    }

    private void setCityFromConfig() {
        mSelectedCity.setText(mConfig.getSelectedCity());
    }

    @Override
    public void onCitySelected(final String city) {
        mConfig.setSelectedCity(city);
        setCityFromConfig();
        listView.getAdapter().notifyDataSetChanged();
    }
}
