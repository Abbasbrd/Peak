package com.anar.PEAK;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class PostCableSizeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CableAdapter postCableAdapter;
    CableAdapter feederCableAdapter;
    Spinner spinner;
    Spinner spinner_cable_size;
    Boolean cond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cable_size);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = findViewById(R.id.cable_size);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onResume() {
        prepareData();
        refreshList();
        super.onResume();
    }

    private void prepareData() {
        cond = true;
        }



    private void refreshList() {
//        postCableAdapter = new CableAdapter(this, R.layout.layout_spinner);
//        feederCableAdapter = new CableAdapter(this,  R.layout.layout_spinner);

//        spinner.setAdapter(postCableAdapter);
//        if (cond) {
//            spinner.setVisibility(View.VISIBLE);
//           // spinner2.setVisibility(View.GONE);
//            spinner.setAdapter(postCableAdapter);
//        } else {
//          //  spinner2.setVisibility(View.GONE);
//            spinner.setVisibility(View.VISIBLE);
//            spinner.setAdapter(feederCableAdapter);
//        }

         //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.CableSize, android.R.layout.simple_spinner_item
        );
         //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using

        String str = (String) parent.getItemAtPosition(position);
        Toast.makeText(this, "cableArrays : "+position +"\nname: " +str, Toast.LENGTH_SHORT).show();


//         Resources res = getResources();
//         String[] cableArrays = res.getStringArray(R.array.CableSize);
//         Toast.makeText(this, "cableArrays : "+position +"\nname: " +cableArrays[position], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}