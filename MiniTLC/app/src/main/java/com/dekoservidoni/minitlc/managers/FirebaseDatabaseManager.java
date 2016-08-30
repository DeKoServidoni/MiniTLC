package com.dekoservidoni.minitlc.managers;

import com.dekoservidoni.minitlc.entities.MiniEvent;
import com.dekoservidoni.minitlc.utils.AppConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class responsible to hold all the functionalities of firebase
 * real time database for android
 *
 * Created by DeKoServidoni on 29/08/16.
 */
public class FirebaseDatabaseManager {

    /** Firebase instance */
    private FirebaseDatabase mFirebaseDatabase;

    /** Database reference instance */
    private DatabaseReference mDatabaseReference;

    /** Callback reference */
    private FirebaseDatabaseCallback mCallback;

    /**
     * Interface responsible to talk with the caller class
     */
    public interface FirebaseDatabaseCallback {
        void onRefreshData(List<MiniEvent> content);
    }

    /**
     * Constructor
     */
    public FirebaseDatabaseManager(FirebaseDatabaseCallback callback) {
        mCallback = callback;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = firebaseDatabase.getReference();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(mCallback != null) {
                    GenericTypeIndicator<HashMap<String, List<MiniEvent>>> listType
                            = new GenericTypeIndicator<HashMap<String, List<MiniEvent>>>() {};
                    HashMap<String, List<MiniEvent>> content = dataSnapshot.getValue(listType);
                    mCallback.onRefreshData(content.get(AppConstants.FIREBASE_TABLE_NAME));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // do nothing
            }
        });
    }

    /*
    Test code for firebase

    FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        List<MiniEvent> test = new ArrayList<>();

        for (int i = 0 ; i < 10 ; i++) {
            MiniEvent miniEvent = new MiniEvent("Titulo " + i,
                    "Descricao " + i,
                    "Quando " + i,
                    "Hora " + i,
                    "Local " + i);

            test.add(miniEvent);
        }

        reference.child(AppConstants.FIREBASE_TABLE_NAME).setValue(test);
     */
}
