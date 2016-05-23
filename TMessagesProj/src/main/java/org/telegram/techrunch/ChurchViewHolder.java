package org.telegram.techrunch;

import android.view.View;
import android.widget.TextView;

import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.techrunch.domain.Church;

/**
 * Created by vlad on 5/20/16.
 */
public class ChurchViewHolder extends ViewHolder {

    public ChurchViewHolder(final View itemView) {
        super(itemView);
    }

    public void bind(final Church church) {
        ((TextView)itemView).setText(church.getDescription());
    }
}
