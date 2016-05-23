package org.telegram.techrunch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.techrunch.domain.Church;

import java.util.List;

/**
 * Created by vlad on 5/20/16.
 */
public class ChurchesAdapter extends RecyclerView.Adapter<ChurchViewHolder> {

    private Context mContext;

    @Override
    public ChurchViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ChurchViewHolder(new TextView(mContext));
    }

    @Override
    public void onBindViewHolder(final ChurchViewHolder holder, final int position) {
        Church church = getChurches().get(position);

        holder.bind(church);
    }

    @Override
    public int getItemCount() {
        return getChurches().size();
    }

    private List<Church> getChurches() {
        return ChurchesController.getInstance().churchesList();
    }
}
