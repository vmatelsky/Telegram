package org.telegram.techrunch.my_churches;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.telegram.messenger.LocaleController;
import org.telegram.messenger.techranch.R;
import org.telegram.techrunch.TechranchConfig;
import org.telegram.techrunch.select_city.SelectCity;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/**
 * Created by vlad on 5/24/16.
 */
public class MyChurchesActivity extends BaseFragment {

    private View noChurchesView;
    private TextView selectedCity;

    @Override
    public View createView(final Context context) {

        Theme.loadRecources(context);

        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        actionBar.setAllowOverlayTitle(true);
        actionBar.setTitle(LocaleController.getString("Techranch_My_Churches", R.string.Techranch_My_Churches));

        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override
            public void onItemClick(int id) {
                if (id == -1) {
                    finishFragment();
                }
            }
        });

        ActionBarMenu menu = actionBar.createMenu();
        ActionBarMenuItem item = menu.addItem(0, R.drawable.ic_ab_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new ActionBarMenuItem.ActionBarMenuItemSearchListener() {
            @Override
            public void onSearchExpand() {

            }

            @Override
            public void onSearchCollapse() {

            }

            @Override
            public void onTextChanged(EditText editText) {

            }
        });
        item.getSearchField().setHint(LocaleController.getString("Search", R.string.Search));

        final LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        fragmentView = layout;

        final View selectCityHeader = MyChurchesActivityHelper.createSelectCityHeader(context);
        selectedCity = (TextView) selectCityHeader.findViewById(R.id.selected_city);
        selectedCity.setPaintFlags(selectedCity.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        selectedCity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                presentFragment(new SelectCity(false));
            }
        });

        layout.addView(selectCityHeader);

        RecyclerListView listView = new RecyclerListView(context);
        listView.setAdapter(new ChurchesAdapter());

        noChurchesView = MyChurchesActivityHelper.createEmptyView(context, new OnClickListener() {
            @Override
            public void onClick(final View view) {
                Toast.makeText(context, "On add church clicked", Toast.LENGTH_SHORT).show();
            }
        });

        layout.addView(noChurchesView, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        TechranchConfig config = new TechranchConfig(getParentActivity());
        selectedCity.setText(config.getSelectedCity());
    }
}
