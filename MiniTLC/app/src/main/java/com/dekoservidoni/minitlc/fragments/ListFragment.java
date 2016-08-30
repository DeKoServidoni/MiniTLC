package com.dekoservidoni.minitlc.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dekoservidoni.minitlc.R;
import com.dekoservidoni.minitlc.adapters.MiniListAdapter;
import com.dekoservidoni.minitlc.entities.MiniEvent;
import com.dekoservidoni.minitlc.managers.CameraManager;
import com.dekoservidoni.minitlc.managers.FirebaseDatabaseManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Class responsible to implement the event list of the app
 */
public class ListFragment extends Fragment implements FirebaseDatabaseManager.FirebaseDatabaseCallback {

    /** UI Components */
    @Bind(R.id.frag_list_view) RecyclerView mRecyclerView;
    @Bind(R.id.frag_progress_view) ProgressBar mProgress;

    /** List adapter */
    private MiniListAdapter mAdapter;

    /** Camera manager instance */
    private CameraManager mManager;

    /** Firebase manager instance */
    private FirebaseDatabaseManager mFirebaseDatabaseManager;

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
        mFirebaseDatabaseManager = new FirebaseDatabaseManager(this);
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

    /// Callback methods

    @Override
    public void onRefreshData(List<MiniEvent> content) {
        mProgress.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.updateContent(content);
    }

    /*
    Test code for firebase

    FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("mini_events");

        List<MiniEvent> test = new ArrayList<>();

        for (int i = 0 ; i < 10 ; i++) {
            MiniEvent miniEvent = new MiniEvent("Titulo " + i,
                    "Descricao " + i,
                    "Quando " + i,
                    "Hora " + i,
                    "Local " + i);

            test.add(miniEvent);
        }

        reference.child("event").setValue(test);
     */
}
