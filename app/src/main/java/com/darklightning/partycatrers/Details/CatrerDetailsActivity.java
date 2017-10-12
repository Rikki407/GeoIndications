package com.darklightning.partycatrers.Details;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.darklightning.partycatrers.Caterers.RelatedAdsFragment;
import com.darklightning.partycatrers.R;

import java.util.ArrayList;

public class CatrerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private String catrerEmail, catrerNo, catrerDetails, catrerName, catrerPrice, catrerLocation, main_img_url, logo_url;
    private TextView email, ph_no, details, title, price, location;
    private ImageView callImage, mailImage, move_right, move_left;
    public CollapsingToolbarLayout collapsing_toolbar;
    Toolbar toolbar;
    LinearLayout first_child;
    ArrayList<String> other_images;
    PhotosPagerAdapter photosPagerAdapter;
    ViewPager photosPager;
    int pager_position = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catrer_details);

        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_button);
        setSupportActionBar(toolbar);
        first_child = (LinearLayout) findViewById(R.id.first_child);
        first_child.setOnClickListener(this);
        move_left = (ImageView) findViewById(R.id.move_left);
        move_right = (ImageView) findViewById(R.id.move_right);
        move_left.setOnClickListener(this);
        move_right.setOnClickListener(this);


        email = (TextView) findViewById(R.id.ads_detail_email);
        ph_no = (TextView) findViewById(R.id.ads_detail_phone);
        details = (TextView) findViewById(R.id.ads_detail_description);
        title = (TextView) findViewById(R.id.ads_detail_title);
        price = (TextView) findViewById(R.id.product_price);
        location = (TextView) findViewById(R.id.ads_detail_address);
        callImage = (ImageView) findViewById(R.id.call_seller);
        mailImage = (ImageView) findViewById(R.id.mail_seller);
        callImage.setOnClickListener(this);
        mailImage.setOnClickListener(this);


        other_images = new ArrayList<>();
        photosPager = (ViewPager) findViewById(R.id.photos_viewpager);

        photosPagerAdapter = new PhotosPagerAdapter(this, other_images, collapsing_toolbar);
        photosPager.setAdapter(photosPagerAdapter);
        photosPager.setOffscreenPageLimit(0);
        photosPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pager_position = position;

                if (pager_position == (other_images.size() - 1))
                    move_right.setVisibility(View.GONE);
                else move_right.setVisibility(View.VISIBLE);

                if (pager_position == 0) move_left.setVisibility(View.GONE);
                else move_left.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            catrerName = extras.getString("catrerName");
            catrerPrice = extras.getString("catrerPrice");
            catrerLocation = extras.getString("catrerLocation");
            catrerEmail = extras.getString("catrerEmail");
            catrerNo = extras.getString("catrerNo");
            catrerDetails = extras.getString("catrerDetails");
            logo_url = extras.getString("logo_url");
            main_img_url = extras.getString("main_img_url");
            setReqDetailsFields();
        }
        Bundle bundle = new Bundle();
        bundle.putString("catrerLocation",catrerLocation);
        RelatedAdsFragment fragment = new RelatedAdsFragment();
        fragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.related_ads_holder, fragment).commit();
    }

    private void setReqDetailsFields() {

        other_images.add(logo_url);
        other_images.add(main_img_url);
        photosPagerAdapter.notifyDataSetChanged();

        if (other_images.size() > 1) {
            move_right.setVisibility(View.VISIBLE);
        }
        email.setText(catrerEmail);
        ph_no.setText(catrerNo);
        details.setText(catrerDetails);
        title.setText(catrerName);
        price.setText(catrerPrice);
        location.setText(catrerLocation);

    }

    private void callSeller(String seller_phno) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + seller_phno));
        startActivity(Intent.createChooser(intent, "Select an app"));
    }

    private void mailSeller(String seller_email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + seller_email));
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_seller:
                if (ph_no.getText() != null && !ph_no.getText().toString().isEmpty())
                    callSeller(ph_no.getText().toString());
                else Toast.makeText(this, "No Phone Number Available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mail_seller:
                if (email.getText() != null && !email.getText().toString().isEmpty())
                    mailSeller(email.getText().toString());
                else Toast.makeText(this, "No Email Available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.move_right :
                pager_position++;
                if(pager_position<other_images.size()) photosPager.setCurrentItem(pager_position);
                break;
            case R.id.move_left :
                pager_position--;
                if(pager_position>=0) photosPager.setCurrentItem(pager_position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
