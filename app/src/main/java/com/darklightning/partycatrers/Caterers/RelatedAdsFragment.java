package com.darklightning.partycatrers.Caterers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class RelatedAdsFragment extends Fragment{
    RecyclerView recyclerView;
    ArrayList<CatrersListItems> mCatrersList;
    RelatedAdsAdapter mAdapter;
    DatabaseReference mRootRef;
    DatabaseReference mCatrersListRef;
    String location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_list,container,false);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        location = getArguments().getString("catrerLocation");
        mCatrersListRef = mRootRef.child("CatrersList");
        mCatrersList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity() ,LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new RelatedAdsAdapter(getActivity(),mCatrersList);
        recyclerView.setAdapter(mAdapter);
        fetchCatrersList();
        return view;
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
                    if (listItems.state_name != null && listItems.state_name.equals(location))
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
