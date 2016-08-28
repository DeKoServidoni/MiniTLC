package com.dekoservidoni.minitlc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dekoservidoni.minitlc.R;
import com.dekoservidoni.minitlc.adapters.MiniListAdapter;
import com.dekoservidoni.minitlc.managers.CameraManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Class responsible to implement the event list of the app
 */
public class ListFragment extends Fragment {

    /** UI Components */
    @Bind(R.id.frag_list_view) RecyclerView mRecyclerView;

    /** List adapter */
    private MiniListAdapter mAdapter;

    /** Camera manager instance */
    private CameraManager mManager;

    /**
     * Constructor
     */
    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mManager = new CameraManager(getActivity());
        mAdapter = new MiniListAdapter();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    /// Action handlers

    @OnClick(R.id.frag_list_fab_take_picture)
    public void onFbTakePictureClick() {
        mManager.takePicture();
    }

    /// Public methods

    /**
     * Handle the callback from camera activity
     */
    public void setPictureReturn() {
        mManager.handleReturn();
    }
}
