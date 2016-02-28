package com.dekoservidoni.minitlc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class MiniListAdapter extends BaseAdapter {

    /** Adapter content */
    private List<MiniEvent> mContent = new ArrayList<>();

    /** Layout inflater */
    private LayoutInflater mInflater = null;

    /**
     * Constructor
     *
     * @param context
     *          Application context
     */
    public MiniListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Set the content of the adapter
     *
     * @param newContent
     *          New content to be setted
     */
    public void setContent(List<MiniEvent> newContent) {
        mContent.clear();
        mContent.addAll(newContent);
        this.notifyDataSetChanged();
    }

    /**
     * Class responsible to implement the list recycle
     */
    private class ViewHolder {
        TextView title;
        TextView date;
        TextView description;
    }

    @Override
    public int getCount() {
        return mContent.size();
    }

    @Override
    public MiniEvent getItem(int position) {
        return mContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        // not used
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null) {
            view = mInflater.inflate(R.layout.event_list_row, null);

            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.list_row_title);
            holder.description = (TextView) view.findViewById(R.id.list_row_description);
            holder.date = (TextView) view.findViewById(R.id.list_row_date);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        MiniEvent event = mContent.get(position);

        holder.title.setText(event.getTitle());
        holder.date.setText(event.getDate());

        int visibility = event.getDescription().isEmpty() ? View.INVISIBLE : View.VISIBLE;
        holder.description.setText(event.getDescription());
        holder.description.setVisibility(visibility);

        return view;
    }
}
