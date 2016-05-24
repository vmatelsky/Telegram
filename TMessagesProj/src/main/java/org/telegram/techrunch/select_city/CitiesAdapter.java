package org.telegram.techrunch.select_city;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.telegram.messenger.R;

import java.util.List;

/**
 * Created by vlad on 5/23/16.
 */
public class CitiesAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private static final int HEADER = 0;
    private static final int ITEM = 1;

    private final Context mContext;
    private final List<String> mData;
    private List<String> mFiltered;
    private final OnCityClickedListener mListener;

    public CitiesAdapter(Context context, List<String> data, OnCityClickedListener listener) {
        mContext = context;
        mData = data;
        mFiltered = mData;
        mListener = listener;
    }

    @Override
    public CityViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        int layoutId = viewType == HEADER ? R.layout.select_city_header : R.layout.select_city_item_layout;

        View itemView = LayoutInflater.
                from(mContext).
                inflate(layoutId, parent, false);

        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CityViewHolder holder, final int position) {
        if (position > 0) {
            holder.bind(mContext, mFiltered.get(position - 1), mListener);
        }
    }

    @Override
    public int getItemCount() {
        return mFiltered.size() + 1;
    }

    @Override
    public int getItemViewType(final int position) {
        if (position == 0) {
            return HEADER;
        }
        return ITEM;
    }

}
