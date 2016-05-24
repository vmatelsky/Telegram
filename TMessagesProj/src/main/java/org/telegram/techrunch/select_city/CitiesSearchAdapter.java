package org.telegram.techrunch.select_city;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import org.telegram.messenger.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 5/24/16.
 */
public class CitiesSearchAdapter extends RecyclerView.Adapter<CityViewHolder> implements Filterable {

    private static final int HEADER = 0;
    private static final int ITEM = 1;

    private final Context mContext;
    private final List<String> mData;
    private List<String> mFiltered;
    private final OnCityClickedListener mListener;

    public CitiesSearchAdapter(Context context, final List<String> data, OnCityClickedListener listener) {
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

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {
                mFiltered = (List<String>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<String> FilteredArrList = new ArrayList<String>();

                if (constraint == null || constraint.length() == 0) {
                    // set the Original result to return
                    results.count = mData.size();
                    results.values = mData;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mData.size(); i++) {
                        String data = mData.get(i);
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(data);
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}