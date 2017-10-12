package com.darklightning.partycatrers.Caterers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.darklightning.partycatrers.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by rikki on 10/11/17.
 */

public class SayWhatActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    public ArrayList<CatrersListItems> mCatrersList;
    CatrersListAdapter mAdapter;
    DatabaseReference mRootRef;
    DatabaseReference mCatrersListRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_list);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mCatrersListRef = mRootRef.child("CatrersList");
        mCatrersList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new CatrersListAdapter(this,mCatrersList);
        recyclerView.setAdapter(mAdapter);
        fetchCatrersList();
    }
    private void fetchCatrersList()
    {
        mCatrersListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCatrersList.clear();
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    CatrersListItems listItems = child.getValue(CatrersListItems.class);
                    mCatrersList.add(listItems);

                    if (listItems != null) {
                        Log.e("show_items_name",""+listItems.catrers_name);
                    }

                }

                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
