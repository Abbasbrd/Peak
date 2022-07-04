package com.anar.PEAK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CableSizeActivity extends AppCompatActivity  {

    ListView cableSizelistview;
    EditText searchCableSize;
    List<String> cableArray;
    List<String> newCableArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cable_size);

        cableSizelistview = findViewById(R.id.cableSizelistview);
        EditText searchCableSize = findViewById(R.id.searchCableSize);

        Resources res = getResources();
        String[] resCableArrayString = res.getStringArray(R.array.CableSize);
        cableArray = Arrays.asList(resCableArrayString);
        prepareData();
        cableSizelistview.setOnItemClickListener((adapterView, view, i, l) -> {

            if(newCableArray==null || newCableArray.isEmpty()){newCableArray=cableArray;}
            String str = (String) newCableArray.get(i);
            Toast.makeText(this, "cableArrays : "+i +"\nname: " +str, Toast.LENGTH_SHORT).show();

        });


        searchCableSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    Toast.makeText(CableSizeActivity.this, s, Toast.LENGTH_SHORT).show();
                    List<String> newCableArray = filterCableSizes(cableArray,s.toString());
                    refreshList(newCableArray);
                }
                else{
                    refreshList(cableArray);
                }
            }
        });





    }


    private void prepareData() {
//        ArrayAdapter<CharSequence> cableSizeAadapter = ArrayAdapter.createFromResource(this,
//                R.array.CableSize, android.R.layout.simple_list_item_1
//        );
//

        ArrayAdapter<String> cableSizeAadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cableArray);
        cableSizelistview.setAdapter(cableSizeAadapter);


    }

    private void refreshList(List<String> cableArray) {
        ArrayAdapter<String> cableSizeAadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cableArray);
        cableSizelistview.setAdapter(cableSizeAadapter);
        }



    private List<String>  filterCableSizes(List<String> cableSizes,String searchValue){
        List<String>  newCableSizes = new ArrayList<>();
        for(String cableSize:cableSizes){
            if(cableSize.contains(searchValue)){
                newCableSizes.add(cableSize);
            }
        }
        return newCableSizes;

    }
}