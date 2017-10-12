package com.darklightning.partycatrers.Caterers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.darklightning.partycatrers.Details.CatrerDetailsActivity;
import com.darklightning.partycatrers.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rikki on 10/11/17.
 */

public class RelatedAdsAdapter extends RecyclerView.Adapter<RelatedAdsAdapter.RelatedViewHolder>  {
    private Context mContext;
    private ArrayList<CatrersListItems> catrersList;

    public RelatedAdsAdapter(Context mContext, ArrayList<CatrersListItems> catrersList) {
        this.mContext = mContext;
        this.catrersList = catrersList;
    }

    @Override
    public RelatedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.related_ads_item,parent,false);
        return new RelatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RelatedViewHolder holder, int position) {
        CatrersListItems catrersListItems = catrersList.get(position);
        if(holder.catrerName!=null)holder.catrerName.setText(catrersListItems.catrers_name);
        if(holder.catrerPrice!=null)holder.catrerPrice.setText("₹ "+catrersListItems.Price+" /-");
        if(holder.catrerLocation!=null)holder.catrerLocation = catrersListItems.state_name;
        holder.catrerEmail = catrersListItems.email;
        holder.catrerNo = catrersListItems.contact_no;
        holder.catrerDetails = catrersListItems.detail;
        holder.logo_url = catrersListItems.catrers_logo_pic;
        holder.main_img_url = catrersListItems.main_pic;
        Log.e("crazy","jhg"+catrersListItems.catrers_name);
        Picasso.with(mContext).load(catrersListItems.catrers_logo_pic).into(holder.catrers_logo_pic);

    }

    @Override
    public int getItemCount() {
        return catrersList.size();
    }

    public class RelatedViewHolder extends RecyclerView.ViewHolder {
        ImageView catrers_logo_pic;
        TextView catrerName,catrerPrice;
        String catrerEmail,catrerNo,catrerDetails,logo_url,main_img_url,catrerLocation;
        public RelatedViewHolder(View itemView) {
            super(itemView);
            catrers_logo_pic = (ImageView)itemView.findViewById(R.id.trending_ad_image);
            catrerName = (TextView) itemView.findViewById(R.id.trending_ad_title);
            catrerPrice = (TextView) itemView.findViewById(R.id.trending_ad_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context item_context  = v.getContext();
                    Intent intent = new Intent(item_context, CatrerDetailsActivity.class);
                    intent.putExtra("catrerName",catrerName.getText().toString());
                    intent.putExtra("catrerPrice",catrerPrice.getText().toString());
                    intent.putExtra("catrerLocation",catrerLocation);
                    intent.putExtra("catrerEmail",""+catrerEmail);
                    intent.putExtra("catrerNo",""+catrerNo);
                    intent.putExtra("catrerDetails",""+catrerDetails);
                    intent.putExtra("logo_url",""+logo_url);
                    intent.putExtra("main_img_url",""+main_img_url);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}

