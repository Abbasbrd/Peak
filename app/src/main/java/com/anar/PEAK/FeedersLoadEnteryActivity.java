package com.anar.PEAK;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public
class FeedersLoadEnteryActivity extends AppCompatActivity {
    private String postCode;
    private int postCap, r, s, t, n,tr,st, rs,rn,tn,sn;
    private Post post;
    private PostLoad postLoad;
    private PostVoltage postVoltage;
    private List<FeederLoad> feederLoadList;
    private int i = 1, feederCount = 1;
    private LinearLayout l_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeders_load_entery);
        l_l = findViewById(R.id.layout_feeder_parrent);
        feederLoadList = new ArrayList<>();

        postCode = getIntent().getStringExtra("postCode");
        postCap = getIntent().getIntExtra("postCap", 0);

//        Toast.makeText(this, "feeders: \n"+postCode+"\n"+postCap, Toast.LENGTH_LONG).show();

        r = getIntent().getIntExtra("r", 0);
        s = getIntent().getIntExtra("s", 0);
        t = getIntent().getIntExtra("t", 0);
        n = getIntent().getIntExtra("n", 0);
        tr = getIntent().getIntExtra("tr",0);
        st = getIntent().getIntExtra("st",0);
        rs = getIntent().getIntExtra("rs",0);
        rn = getIntent().getIntExtra("rn",0);
        sn = getIntent().getIntExtra("sn",0);
        tn = getIntent().getIntExtra("tn",0);


        postLoad = new PostLoad(r, s, t, n);
        postVoltage = new PostVoltage(tr, st,rs, rn, sn, tn);
        post = new Post() {{
            setPostCode(postCode);
            setPostCapacity(postCap);
            setLoad(postLoad);
            setVoltage(postVoltage);
        }};
        post.setLoadList(feederLoadList);

        //  setTextChangedListenerforLoadGroup();
        feederCount = 5;
        for (int ind = 1; ind <= feederCount; ind++) {
            generate_next_Feeder();
            i++;
        }

    }

    private void setTextChangedListenerforLoadGroup(View view) {
        EditText r = view.findViewById(R.id.input_R);
//        r.setOnFocusChangeListener(this::onFocusChange);
        r.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyText(r);
            }
        });
        EditText s = view.findViewById(R.id.input_S);
//        s.setOnFocusChangeListener(this::onFocusChange);
        s.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyText(s);
            }
        });
        EditText t = view.findViewById(R.id.input_T);
//        t.setOnFocusChangeListener(this::onFocusChange);
        t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyText(t);
            }
        });
        EditText n = view.findViewById(R.id.input_N);
//        n.setOnFocusChangeListener(this::onFocusChange);
        n.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyText(n);
            }
        });
//        Toast.makeText(this, "nextfeeder entered"+"\nr"+r.getText().toString(), Toast.LENGTH_SHORT).show();

        TextView tv = view.findViewById(R.id.txt_feeder_number);
//        if (!TextUtils.isEmpty(tv.getText())) {
        if (true) {
            // i = i + 1;
            String str = "F" + (i);
            tv.setText(str);

            r.setTag(str + "_R");
            s.setTag(str + "_S");
            t.setTag(str + "_T");
            n.setTag(str + "_N");
            feederLoadList.add(new FeederLoad());
//            i+=1;
        }
    }

    private void setTextChangedListenerforLoadGroup() {
        EditText r = findViewById(R.id.input_R);
//        r.setOnFocusChangeListener(this::onFocusChange);

        r.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyText(r);
            }
        });

        EditText s = findViewById(R.id.input_S);
//        s.setOnFocusChangeListener(this::onFocusChange);
        s.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyText(s);
            }
        });

        EditText t = findViewById(R.id.input_T);
//        t.setOnFocusChangeListener(this::onFocusChange);
        t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyText(t);
            }
        });

        EditText n = findViewById(R.id.input_N);
//        n.setOnFocusChangeListener(this::onFocusChange);
        n.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyText(n);
            }
        });
//        Toast.makeText(this, "FirstFeeder entered" + "\nr" + r.getText().toString(), Toast.LENGTH_SHORT).show();
        String str = "F1";
        r.setTag(str + "_R");
        s.setTag(str + "_S");
        t.setTag(str + "_T");
        n.setTag(str + "_N");
        feederLoadList.add(new FeederLoad());

    }

    public void reg_OnClick(View view) {

        view.setFocusable(true);
        view.requestFocus();
        JSONObject json = PostJsonParser.postToJsonObject_Array(post);
//        Toast.makeText(this, postCode + " " + postCap, Toast.LENGTH_SHORT).show();
        PostJsonParser.writeJsonObject(json, postCode + ".json", FeedersLoadEnteryActivity.this);

        startActivity(new Intent(this, activity_post_list.class));
        this.finish();

    }

    public void next_Feeder_OnClick(View view) {
//        Toast.makeText(this, "nextfeeder entered", Toast.LENGTH_SHORT).show();

        LayoutInflater l_inflater = LayoutInflater.from(FeedersLoadEnteryActivity.this);
//        LayoutInflater l_inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = l_inflater.inflate(R.layout.activity_feeder_load, l_l, false);
        l_l.addView(v);

        setTextChangedListenerforLoadGroup(v);
        i++;
    }

    public void applyText(View view) {

//            Toast.makeText(this, "onFocusChanged", Toast.LENGTH_SHORT).show();
        EditText ed = (EditText) view;
//            Toast.makeText(this, ed.getText().toString(), Toast.LENGTH_SHORT).show();
        if (ed != null & !TextUtils.isEmpty(ed.getText())) {
            String strTag = (String) ed.getTag();
//                Toast.makeText(this, "ed:\n" + ed.getText().toString() + "\n" + strTag, Toast.LENGTH_SHORT).show();
            String phasetag = strTag.substring(3, 4);
            String feedernumber = strTag.substring(1, 2);
//                Toast.makeText(this, "phasetag:" + phasetag+"\nfeedernumber: "+feedernumber, Toast.LENGTH_SHORT).show();
            applyEntry(ed, phasetag, feedernumber);

        }

    }

    private void applyEntry(EditText ed, String phasetag, String feedernumber) {
        String val = "";
        int feeder = Integer.valueOf(feedernumber);
        FeederLoad fl = feederLoadList.get(feeder - 1);
        switch (phasetag) {
            case "R":
                val = ed.getText().toString();
                fl.setPhaseR(val);
                break;
            case "S":
                val = ed.getText().toString();
                fl.setPhaseS(val);
                break;
            case "T":
                val = ed.getText().toString();
                fl.setPhaseT(val);
                break;
            case "N":
                val = ed.getText().toString();
                fl.setNutralN(val);
                break;
        }
    }

    public void generate_next_Feeder() {
//        Toast.makeText(this, "nextfeeder entered", Toast.LENGTH_SHORT).show();

        LayoutInflater l_inflater = LayoutInflater.from(FeedersLoadEnteryActivity.this);
//        LayoutInflater l_inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = l_inflater.inflate(R.layout.activity_feeder_load, l_l, false);
        l_l.addView(v);

        setTextChangedListenerforLoadGroup(v);

    }
}

