package org.telegram.techrunch.select_city;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 5/24/16.
 */
public class CitiesSearchAdapter extends CitiesAdapter implements Filterable {

    private static final int HEADER = 0;
    private static final int ITEM = 1;

    private List<String> mFiltered;

    public CitiesSearchAdapter(Context context, final List<String> data, OnCityClickedListener listener) {
        super(context, data, listener);
        mFiltered = mData;
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