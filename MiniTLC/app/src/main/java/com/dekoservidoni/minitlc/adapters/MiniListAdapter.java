package com.dekoservidoni.minitlc.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        ViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;//mContent.size();
    }
}
