package com.anar.PEAK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Post_details extends AppCompatActivity {

    Post post;
    String postCode;
    private LinearLayout feeder_container;
    int low = 30, norm = 70, border = 80, full = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        feeder_container = findViewById(R.id.feeder_container);

        ViewGroup post_details_layout = findViewById(R.id.post_details_layout);
        postCode = getIntent().getStringExtra("postCode");
        post=Utils.prepareData(this,postCode);
        Utils.bindeData(post_details_layout,post);

//        postCode = getIntent().getStringExtra("postCode");
//        prepareData();
//        bindeData(post);

        List<FeederLoad> feederLoads = post.getLoadList();
        View feeders_load_header = findViewById(R.id.feerders_load_header);
        if (feederLoads.isEmpty()) {
            feeders_load_header.setVisibility(View.GONE);
        } else {
            feeders_load_header.setVisibility(View.VISIBLE);
        }
        int i = 0;
        for (FeederLoad load : feederLoads) {
            next_Feeder(load, i + 1);
            i++;
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_post_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public Boolean onItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.menu_item_edit:
                Intent intent = new Intent(this, edit_post_details.class);
                intent.putExtra("postCode", postCode);
                intent.putExtra("edit_mode", "1");
                startActivity(intent);
                break;
        }
        return true;
    }



    public void next_Feeder(FeederLoad load, int index) {

        LayoutInflater l_inflater = LayoutInflater.from(Post_details.this);
        View v = l_inflater.inflate(R.layout.feeder_load_txt, feeder_container, false);
        feeder_container.addView(v);
        TextView tfn = v.findViewById(R.id.txt_feeder_number);
        TextView ed_R = v.findViewById(R.id.txt_R);
        TextView ed_S = v.findViewById(R.id.txt_S);
        TextView ed_T = v.findViewById(R.id.txt_T);
        TextView ed_N = v.findViewById(R.id.txt_N);
        tfn.setText("F" + index);
        ed_R.setText(String.valueOf(load.getPhaseR()));
        ed_S.setText(String.valueOf(load.getPhaseS()));
        ed_T.setText(String.valueOf(load.getPhaseT()));
        ed_N.setText(String.valueOf(load.getNutralN()));
    }

//    private void bindeData(Post post) {
//        int postCap = post.getPostCapacity();
//        PostLoad pl = post.getLoad();
//
//        TextView tv_postCode = findViewById(R.id.et_postCode);
//        TextView tv_postCap = findViewById(R.id.et_postCap);
//        TextView tv_load_perc = findViewById(R.id.load_perc);
//        TextView tv_load_ave = findViewById(R.id.load_ave);
//        TextView tv_R = findViewById(R.id.r);
//        TextView tv_S = findViewById(R.id.s);
//        TextView tv_T = findViewById(R.id.t);
//        TextView tv_N = findViewById(R.id.n);
//
//        int r = pl.getPhaseR();
//        int s = pl.getPhaseS();
//        int t = pl.getPhaseT();
//        int n = pl.getNutralN();
//        double ave = (r + s + t) / 3;
//        double perc = 100 * ave / (1.44 * postCap);
//        tv_postCode.setText(postCode);
//        setPostCap(tv_postCap, postCap);
//        setPercentage(tv_load_perc, perc);
//        tv_load_ave.setText(String.format("%.1f", ave));
//        tv_R.setText(String.valueOf(r));
//        tv_S.setText(String.valueOf(s));
//        tv_T.setText(String.valueOf(t));
//        tv_N.setText(String.valueOf(n));
//
//
//    }
//
//    private void setPostCap(TextView tv_postCap, int postCap) {
//
//        tv_postCap.setText(postCap + " KVA");
//
//    }
//
//    private void setPercentage(TextView tv_load_perc, double perc) {
//        tv_load_perc.setText(String.format("%.1f", perc) + "%");
//        int color = Color.BLACK;
//        if (perc < low) {
//            color = Color.CYAN;
//        } else if (perc >= low & perc < norm) {
//            color = Color.DKGRAY;
//        } else if (perc >= norm & perc < border) {
//            color = Color.YELLOW;
//        } else if (perc >= border & perc < full) {
//            color = Color.MAGENTA;
//        } else if (perc >= full) {
//            color = Color.RED;
//        }
//        tv_load_perc.setTextColor(color);
//    }
//
//    private void prepareData() {
//        //File internalStorageDir = getFilesDir();
////        Toast.makeText(this, internalStorageDir.getAbsolutePath(), Toast.LENGTH_LONG).show();
//        String jsonString = PostJsonParser.readJsonObject(postCode + ".json", this);
////        if (jsonString.isEmpty()){
////            Toast.makeText(this, postCode + " : the file not found", Toast.LENGTH_LONG).show();}
////        else{
////        Toast.makeText(this, postCode + " : has been readed", Toast.LENGTH_LONG).show();
////            }
//
//        post = PostJsonParser.parseJsonPost_Array(jsonString, this);
//        if (post != null) if (post.getPostCode() != null) if (post.getPostCode().isEmpty())
//            Toast.makeText(this, post + " not initialized", Toast.LENGTH_LONG).show();
//
//    }

}