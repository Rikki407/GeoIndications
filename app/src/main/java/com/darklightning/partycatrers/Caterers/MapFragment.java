package com.darklightning.partycatrers.Caterers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.darklightning.partycatrers.R;

/**
 * Created by rikki on 10/11/17.
 */

public class MapFragment extends Fragment implements View.OnClickListener
{
    ImageView loc_1,loc_2,loc_3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_activity,container,false);
        loc_1 = (ImageView) view.findViewById(R.id.loc_1);
        loc_1.setOnClickListener(this);
        loc_2 = (ImageView) view.findViewById(R.id.loc_2);
        loc_3 = (ImageView) view.findViewById(R.id.loc_3);
        loc_2.setOnClickListener(this);

        loc_3.setOnClickListener(this);


        return view;
    }


    public static Fragment newInstance()
    {
        return new MapFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loc_1:
                Intent intent  = new Intent(getActivity(),SayWhatActivity.class);
                startActivity(intent);
                break;
            case R.id.loc_2:
                Intent intent2  = new Intent(getActivity(),SayWhatActivity.class);
                startActivity(intent2);
                break;
            case R.id.loc_3:
                Intent intent3  = new Intent(getActivity(),SayWhatActivity.class);
                startActivity(intent3);
                break;

        }
    }
}
