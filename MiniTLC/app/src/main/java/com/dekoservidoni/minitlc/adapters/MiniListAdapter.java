package com.dekoservidoni.minitlc.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dekoservidoni.minitlc.R;
import com.dekoservidoni.minitlc.entities.MiniEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to implement the adapter of the event list
 *
 * Created by DeKoServidoni on 2/25/16.
 */
public class MiniListAdapter extends RecyclerView.Adapter<MiniListAdapter.ViewHolder> {

    /** Adapter content */
    private List<MiniEvent> mContent = new ArrayList<>();

    /**
     * View holder to implement the card items
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private MiniEvent event;

        public TextView title;
        public TextView when;
        public TextView where;

        ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.card_event_label);
            when = (TextView) v.findViewById(R.id.card_event_when_label);
            where = (TextView) v.findViewById(R.id.card_event_where_label);
        }

        public void setEvent(MiniEvent miniEvent) {
            this.event = miniEvent;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MiniEvent event = mContent.get(position);

        holder.setEvent(event);
        holder.title.setText(event.getTitle());
        holder.when.setText(event.getWhen());
        holder.where.setText(event.getLocal());
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

    /**
     * Update the adapter's content
     *
     * @param content to be updated
     */
    public void updateContent(List<MiniEvent> content) {
        mContent.clear();
        mContent.addAll(content);
        this.notifyDataSetChanged();
    }
}
