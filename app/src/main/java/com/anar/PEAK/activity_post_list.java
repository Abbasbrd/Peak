package com.anar.PEAK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class activity_post_list extends AppCompatActivity {

    int i = 1;
    List<Post> postlist;
    ListView postListView;
    ArrayAdapter<Post> postAadapter_rst;
    ArrayAdapter<Post> postAadapter_percAve;
    View header_load;
    View header_perc;
    boolean cond;
    private static final int PERMISSION_REQ_CODE = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        postListView = findViewById(R.id.postlistview);
        header_load = findViewById(R.id.header_load);
        header_perc = findViewById(R.id.header_perc);


        postListView.setOnItemClickListener((adapterView, view, i, l) -> {
//            Toast.makeText(this, postlist.get(i).getPostCode(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Post_details.class);
            Post p = postlist.get(i);
            intent.putExtra("postCode", p.getPostCode());
            intent.putExtra("postCap", p.getPostCapacity());
            intent.putExtra("postLoad", p.getLoad());
            startActivity(intent);

        });
//        prepareData();
//        refreshList();
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }

    @Override
    public void onResume() {
        prepareData();
        refreshList();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_list_view, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public Boolean onItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.menu_item_rst:
                cond = true;
                refreshList();
                break;
            case R.id.menu_item_ave:
                cond = false;
                refreshList();
                break;
            case R.id.menu_item_export_excel:
                ecportExcel();
                break;
            case R.id.menu_item_get_location:
                checkPermissions();
                GpsUtils gpsUtil = new GpsUtils(this);
                if (gpsUtil.getCanGetLocation()) {
                    double latitude = gpsUtil.getLatitude();
                    double longitude = gpsUtil.getLongitude();
                    Toast.makeText(this, "location is:\n"+"latitude: "+latitude+"\nlongitude: "+longitude,
                            Toast.LENGTH_SHORT).show();

                }else{
                    gpsUtil.showGpsAlertDialog();
                }
        break;

    }
        return true;
}

    private Boolean checkPermissions() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQ_CODE);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_REQ_CODE);
            return true;
        }
        return true;
        // else Toast.makeText(this, "Has Permission ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied to access location", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void ecportExcel() {
        if (postlist.isEmpty()) {
            Toast.makeText(this, "List is Empty", Toast.LENGTH_SHORT).show();
        } else {
//            ExportExcelPostLoad ee = new ExportExcelPostLoad(activity_post_list.this, postlist);
//            ee.ExportExcel();
            Excel_Util_POI ee = new Excel_Util_POI(activity_post_list.this, postlist);
            ee.write_post();
        }

    }

    private void prepareData() {
        postlist = new ArrayList<>();
        File internalStorageDir = getFilesDir();
//        Toast.makeText(this, internalStorageDir.getAbsolutePath(), Toast.LENGTH_LONG).show();
        String[] files1 = Utils.getFileList(internalStorageDir, ".json");
//        if (files!=null){
//            Toast.makeText(this, "Filter Files ount"+files.length, Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "Files rertuns null", Toast.LENGTH_SHORT).show();
//        }
//        String[] files1 = internalStorageDir.list();
        String ss = "no file\n";

        if (files1 != null) if (files1.length > 0) {
            int len = files1.length;
            ss = "file Count:\n " + len + "\n";


//            Toast.makeText(this, ss.toString(), Toast.LENGTH_LONG).show();
            for (int it = 0; it < len; it++) {
                String jsonString = PostJsonParser.readJsonObject(files1[it], this);
                Post post = PostJsonParser.parseJsonPost_Array(jsonString, this);
//                if (post.getLoadList() == null) {
//                    Toast.makeText(this, "getLoadList is null", Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(this, "getLoadList: " + post.getLoadList().size(), Toast.LENGTH_LONG).show();
//                }


//                String str1 = post.getDateCreated();
//                Toast.makeText(this, str1, Toast.LENGTH_SHORT).show();
                postlist.add(post);
            }
        }


//        String jsonString = PostJsonParser.readJsonObject("30058.json", this);
//        String jsonString1 = PostJsonParser.readJsonObject("30070.json", this);
//        String jsonString2 = PostJsonParser.readJsonObject("30068.json", this);
//        Toast.makeText(this, jsonString, Toast.LENGTH_LONG).show();
//        Post post = PostJsonParser.parseJsonPost_Array(jsonString);
//        postlist.add(post);
//        Post post1 = PostJsonParser.parseJsonPost_Array(jsonString1);
//        postlist.add(post1);
//        Post post2 = PostJsonParser.parseJsonPost_Array(jsonString2);
//        postlist.add(post2);
    }

    private void refreshList() {
        postAadapter_rst = new PostAdapter(this, android.R.layout.simple_list_item_1, postlist);
        postAadapter_percAve = new PostAdapterPercAve(this, android.R.layout.simple_list_item_1, postlist);

        if (cond) {
            header_load.setVisibility(View.VISIBLE);
            header_perc.setVisibility(View.GONE);
            postListView.setAdapter(postAadapter_rst);
        } else {
            header_load.setVisibility(View.GONE);
            header_perc.setVisibility(View.VISIBLE);
            postListView.setAdapter(postAadapter_percAve);
        }
    }


    public void regNew_OnClick(View view) {

        startActivity(new Intent(this, form_info.class));
    }

    public void exit_OnClick(View view) {
        finish();
    }
}