package com.anar.PEAK;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class form_info extends AppCompatActivity {

    // Load editText Controller
    EditText edR;
    EditText edS;
    EditText edT;
    EditText edN;

    // line Voltage editText Controller
    EditText edTR;
    EditText edST;
    EditText edRS;

    // phase Voltage editText Controller
    EditText edRN;
    EditText edSN;
    EditText edTN;

    // Post editText Controller
    EditText p_code;
    EditText p_cap;

    // Load section divition
    LinearLayout reg_Load_div;

//    LinearLayout layout_line_voltage;
//    LinearLayout layout_phase_voltage;

    // Load variables
    int r;
    int s;
    int t;
    int n;
    int tr;
    int st;
    int rs;
    int rn;
    int tn;
    int sn;

    // Post variables
    int p_Cap;
    String p_Code;

    //Local variables
    int num_Load;
    int p_Load_Status;
    int line_voltage= 400;
    int phase_voltage=240;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_info);

        // Here find each element in user interface side that we need
        edR = findViewById(R.id.input_R);
        edS = findViewById(R.id.input_S);
        edT = findViewById(R.id.input_T);
        edN = findViewById(R.id.input_N);
        edTR= findViewById(R.id.input_TR);
        edST= findViewById(R.id.input_ST);
        edRS= findViewById(R.id.input_RS);
        edRN= findViewById(R.id.input_RN);
        edSN= findViewById(R.id.input_SN);
        edTN= findViewById(R.id.input_TN);
        p_cap = findViewById(R.id.input_post_capacity);
        p_code = findViewById(R.id.input_post_code);
        reg_Load_div = findViewById(R.id.reg_Load_div);


        // Here by the addTextChangedListener we can save each EditText value in its variable;
        p_cap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (TextUtils.isEmpty(editable)
                    | TextUtils.isEmpty(p_code.getText())) {
                    reg_Load_div.setVisibility(View.INVISIBLE);
                }
                else if (p_code.getText().toString().length()!=5
                        |p_code.getText().toString().startsWith("0")
                        |Integer.valueOf(editable.toString())==0){
                         reg_Load_div.setVisibility(View.INVISIBLE);
                }
                else {
                    reg_Load_div.setVisibility(View.VISIBLE);
                }

                if (TextUtils.isEmpty(editable)){
                    p_Cap=0;
                }
                else p_Cap = Integer.valueOf(editable.toString());
                num_Load = Utils.getNominalLoad(p_Cap);;
                r = OverCurrentAlert(edR);
                s = OverCurrentAlert(edS);
                t = OverCurrentAlert(edT);
                n = OverCurrentAlert(edN);


            }
        });

        p_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (TextUtils.isEmpty(editable)
                        | TextUtils.isEmpty(p_cap.getText())){
                    reg_Load_div.setVisibility(View.INVISIBLE);
                }
                else if(Integer.valueOf(p_cap.getText().toString())==0
                        |editable.toString().length()!=5
                        |editable.toString().startsWith("0")) {
                    reg_Load_div.setVisibility(View.INVISIBLE);
                }
                else {
                    reg_Load_div.setVisibility(View.VISIBLE);
                }

                if (TextUtils.isEmpty(editable)){
                    p_Code="0";
                }
                else p_Code=editable.toString();


            }
        });

        edR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

               r= OverCurrentAlert(edR);
            }
        });
        edS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

              s=  OverCurrentAlert(edS);



            }
        });
        edT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

              t= OverCurrentAlert(edT);




            }
        });
        edN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

              n =  OverCurrentAlert(edN);




            }
        });

        edTR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tr= LineOverVoltageAlert(edTR);

            }
        });
        edST.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                st= LineOverVoltageAlert(edST);

            }
        });
        edRS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                rs= LineOverVoltageAlert(edRS);

            }
        });

        edRN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                rn= PhaseOverVoltageAlert(edRN);

            }
        });
        edTN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tn= PhaseOverVoltageAlert(edTN);

            }
        });
        edSN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sn= PhaseOverVoltageAlert(edSN);
            }
        });
    }

    private int LineOverVoltageAlert(View view) {
        EditText ed = (EditText) view;
        int voltage;
        if (TextUtils.isEmpty(ed.getText())){
            voltage=0;
        }
        else
            voltage = Integer.valueOf(ed.getText().toString());

        if (voltage>line_voltage || voltage < 0.8*line_voltage){
            view.setBackgroundColor(Color.RED);
        }
        else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        return voltage;
    }

    private int PhaseOverVoltageAlert(View view) {
        EditText ed = (EditText) view;
        int voltage;
        if (TextUtils.isEmpty(ed.getText())){
            voltage=0;
        }
        else
            voltage = Integer.valueOf(ed.getText().toString());

        if (voltage>phase_voltage || voltage < 0.8*phase_voltage){
            view.setBackgroundColor(Color.RED);
        }
        else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        return voltage;
    }

    private int OverCurrentAlert(View view) {

        EditText ed = (EditText) view;
        int load;
        if (TextUtils.isEmpty(ed.getText())){
            load=0;
        }
        else
        load = Integer.valueOf(ed.getText().toString());

        if (load>num_Load){
            view.setBackgroundColor(Color.RED);
        }
        else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        return load;
    }


    private void ToggleEditable(){
        ToggleFocusable(edR);
        ToggleCursorVisible(edR);
        ToggleFocusable(edS);
        ToggleCursorVisible(edS);
        ToggleFocusable(edT);
        ToggleCursorVisible(edT);
        ToggleFocusable(edN);
        ToggleCursorVisible(edN);
        ToggleFocusable(p_cap);
        ToggleCursorVisible(p_cap);
        ToggleFocusable(p_code);
        ToggleCursorVisible(p_code);
    }

    private void ToggleFocusable(View view){
        boolean b = !view.isFocusable();
        view.setFocusable(b);
    }

    private void ToggleCursorVisible(EditText view){
        boolean b = !view.isFocusable();
        view.setCursorVisible(b);
    }

    private void ShowProgressDialog(){
        ProgressDialog p_dialoge = new ProgressDialog(this);
        p_dialoge.setCancelable(false);
        p_dialoge.setTitle("در حال ثبت اطلاعات");
        p_dialoge.setMessage("...");
        setProgressBarIndeterminate(true);
        p_dialoge.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                p_dialoge.dismiss();
            }
        },2000L);
    }

    //typical dialog
    private void ShowAlertDialog(){
        Post current_post = new Post(){
            {
                setPostCode(p_Code);
                setPostCapacity(p_Cap);
                setLoad(new PostLoad(r,s,t,n));
                setVoltage(new PostVoltage(tr,st, rs, rn, sn, tn));
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_reg_load_title)
        .setMessage(R.string.dialog_reg_load_msg)
        .setCancelable(false)
        .setIcon(R.drawable.ic_launcher_background)
        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                startActivity(new Intent(form_info.this, activity_post_list.class));
//                ShowProgressDialog();
//                finish();
//                ShowMultyChoiseAlertDialog();
                Intent intent =new Intent(form_info.this, FeedersLoadEnteryActivity.class);
                intent.putExtra("postCode",p_Code);
                intent.putExtra("postCap",p_Cap);
                intent.putExtra("r",r);
                intent.putExtra("s",s);
                intent.putExtra("t",t);
                intent.putExtra("n",n);
                intent.putExtra("tr",tr);
                intent.putExtra("st",st);
                intent.putExtra("rs",rs);
                intent.putExtra("rn",rn);
                intent.putExtra("sn",sn);
                intent.putExtra("tn",tn);

//                Toast.makeText(form_info.this, "form_info: \npostCode: "+p_Code+"\npostCap: "+p_Cap+"\nR: "+r, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        })
        .setNegativeButton("خیر", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //test cableSize
                startActivity(new Intent(form_info.this, PostCableSizeActivity.class));

                //

//                JSONObject json = PostJsonParser.postToJsonObject_Array(current_post);
//                PostJsonParser.writeJsonObject(json,p_Code+".json",form_info.this);
//                startActivity(new Intent(form_info.this, activity_post_list.class));
//                ShowCustomDialog();
                finish();

            }
        });
        builder.show();
    }

    //singleChoise dialog
    //message should not to be set
    //checkeditem = -1 means no item have been choosed
    private void ShowSingleChoiseAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_reg_load_title)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_background)
                .setSingleChoiceItems(new String[]{"Item0","Item1","Item2"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // i return the checked item number start from 0 for first item
                    }
                })
                .setPositiveButton("OK",null);
        builder.show();
    }
    //multyChoise dialog
    //message should not to be set
    private void ShowMultyChoiseAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_reg_load_title)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_background)
                .setMultiChoiceItems(new String[]{"برقگیر دارد؟","کات اوت دارد؟","کنتور روشنایی دارد؟","کلیدکل دارد؟"}, new boolean[]{false,false,false,false}, new DialogInterface.OnMultiChoiceClickListener() {
                    private DialogInterface dialogInterface;

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    //on each item clicked, i returns the specified item number and b return isChecked value for that item

                    }
                })
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                ShowAlertDialog();

            }
        });
        builder.show();
    }

    //Custom dialog
    //Show any activity as a dialog
    private void ShowCustomDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_feeders_load_entery);
        dialog.show();
    }


//        you can use any activity as a dialog by this way
    public void reg_OnClick(View view){


        Button btn= (Button)view;
        if (btn.getId() == R.id.btn_reg_load){


            LoadDto ld= new LoadDto(r,s,t,n,p_Cap);
            LoadStatus ls= Utils.CheckLoadStatus(ld);

            p_Load_Status=ls.getStatus();
            Toast.makeText(this, ls.getMessage(), Toast.LENGTH_SHORT).show();

            ToggleEditable();

            ShowMultyChoiseAlertDialog();

             /*
        Here must send form data to database
        r
        s
        t
        n
        p_Code
        p_Cap
        p_Load_Status
        */
        }


    }
}