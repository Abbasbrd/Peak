package com.anar.PEAK;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public class edit_post_details extends AppCompatActivity {

    Post post;
    String postCode;
    private LinearLayout feeder_container;
    int low = 30, norm = 70, border = 80, full = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post_details);

        feeder_container = findViewById(R.id.feeder_container);

        ViewGroup edit_post_layout = findViewById(R.id.edit_post_layout);
        postCode = getIntent().getStringExtra("postCode");
        post=Utils.prepareData(this,postCode);
        Utils.bindeData(edit_post_layout,post);

        List<FeederLoad> feederLoads = post.getLoadList();
        View feeders_load_header = findViewById(R.id.feerders_load_header);
        if (feederLoads.isEmpty()) {
            feeders_load_header.setVisibility(View.GONE);
        } else {
            feeders_load_header.setVisibility(View.VISIBLE);
        }
        int i = 0;
        for (FeederLoad load : feederLoads) {
            Utils.next_Feeder(this,feeder_container,load, i + 1);
            i++;
        }
    }

}

