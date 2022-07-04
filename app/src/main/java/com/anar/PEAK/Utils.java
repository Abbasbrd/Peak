package com.anar.PEAK;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Calendar;

public class Utils {

    private static final int low = 30;
    private static final int norm = 70;
    private static final int border = 80;
    private static final int full = 100;

    public static String convertInputStreamToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            while (bis.available() != 0) {
                sb.append((char) bis.read());
            }
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String[] getFileList(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.list();
    }

    public static String[] getFileList(String directoryPath, String fileExtentionFilter) {
        File directory = new File(directoryPath);
        return getFileList(directory, fileExtentionFilter);
    }

    public static File[] getFiles(String directoryPath, String fileExtentionFilter) {
        File directory = new File(directoryPath);
        return getFiles(directory, fileExtentionFilter);
    }

    public static String[] getFileList(File directory, String fileExtentionFilter) {
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                String lowercase = name.toLowerCase();
                return lowercase.endsWith(fileExtentionFilter);
            }
        };
        String[] fileList = directory.list(filter);
        return fileList;
    }

    public static File[] getFiles(File directory, String fileExtentionFilter) {
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                String lowercase = name.toLowerCase();
                return lowercase.endsWith(fileExtentionFilter);
            }
        };
        File[] fileList = directory.listFiles(filter);
        return fileList;
    }

    public static Post prepareData(Context context, String postCode) {
        // File internalStorageDir = context.getFilesDir();
//        Toast.makeText(this, internalStorageDir.getAbsolutePath(), Toast.LENGTH_LONG).show();
        String jsonString = PostJsonParser.readJsonObject(postCode + ".json", context);
//        if (jsonString.isEmpty()){
//            Toast.makeText(this, postCode + " : the file not found", Toast.LENGTH_LONG).show();}
//        else{
//        Toast.makeText(this, postCode + " : has been readed", Toast.LENGTH_LONG).show();
//            }

        Post post = PostJsonParser.parseJsonPost_Array(jsonString, context);
        if (post != null) if (post.getPostCode() != null) if (post.getPostCode().isEmpty())
            Toast.makeText(context, post + " not initialized", Toast.LENGTH_LONG).show();
        return post;

    }

    public static void bindeData(ViewGroup viewGroup, Post post) {
        int postCap = post.getPostCapacity();
        PostLoad pl = post.getLoad();
        String postCode = post.getPostCode();

        TextView tv_postCode = viewGroup.findViewById(R.id.et_postCode);
        TextView tv_postCap = viewGroup.findViewById(R.id.et_postCap);
        TextView tv_load_perc = viewGroup.findViewById(R.id.load_perc);
        TextView tv_load_ave = viewGroup.findViewById(R.id.load_ave);
        TextView tv_R = viewGroup.findViewById(R.id.r);
        TextView tv_S = viewGroup.findViewById(R.id.s);
        TextView tv_T = viewGroup.findViewById(R.id.t);
        TextView tv_N = viewGroup.findViewById(R.id.n);

        int r = pl.getPhaseR();
        int s = pl.getPhaseS();
        int t = pl.getPhaseT();
        int n = pl.getNutralN();
        double ave = (r + s + t) / 3;
        double perc = 100 * ave / (1.44 * postCap);
        tv_postCode.setText(postCode);
        setPostCap(tv_postCap, postCap);
        setPercentage(tv_load_perc, perc);
        tv_load_ave.setText(String.format("%.1f", ave));
        tv_R.setText(String.valueOf(r));
        tv_S.setText(String.valueOf(s));
        tv_T.setText(String.valueOf(t));
        tv_N.setText(String.valueOf(n));


    }

    private static void setPostCap(TextView tv_postCap, int postCap) {

        tv_postCap.setText(postCap + " KVA");

    }

    private static void setPercentage(TextView tv_load_perc, double perc) {
        tv_load_perc.setText(String.format("%.1f", perc) + "%");
        int color = Color.BLACK;
        if (perc < low) {
            color = Color.CYAN;
        } else if (perc >= low & perc < norm) {
            color = Color.DKGRAY;
        } else if (perc >= norm & perc < border) {
            color = R.color.yellow;
        } else if (perc >= border & perc < full) {
            color = R.color.orange;
        } else if (perc >= full) {
            color = Color.RED;
        }
        tv_load_perc.setTextColor(color);
    }

    public static void next_Feeder(Context context, ViewGroup viewGroup, FeederLoad load, int index) {

        LayoutInflater l_inflater = LayoutInflater.from(context);
        View v = l_inflater.inflate(R.layout.feeder_load_txt, viewGroup, false);
        viewGroup.addView(v);
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

    public static String parseDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.setTime(currentDate);

        // Get year from date
        int year = calendar.get(Calendar.YEAR);
        // Get month from date
        int month = calendar.get(Calendar.MONTH)+1;

        // Get day from date
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //In this case, the month count is started at 0. So you have to add 1 for display.
        return year + "/" + month+ "/" + day;
    }

    public static String parseHijriDate() {

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.setTime(currentDate);

        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        Date currentJalaliDate = jalaliCalendar.getTime();
        jalaliCalendar.setTime(currentJalaliDate);

        String jalaliDateStr = jalaliCalendar.getJalaliDate(currentJalaliDate);



        return jalaliDateStr;
    }

    public static String parseTime() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.setTime(currentDate);


        // Get day from date
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // Get month from date
        int minute = calendar.get(Calendar.MINUTE);

        // Get year from date
        int second = calendar.get(Calendar.SECOND);

        return hour + ":" + minute + ":" + second;
    }

    public static LoadStatus CheckLoadStatus(LoadDto load) {

       int r =load.getR();
       int s =load.getS();
       int t =load.getT();
       int n =load.getN();
       int cap = load.getCap();
       int num_Load = Utils.getNominalLoad(cap);

    LoadStatus ls = new LoadStatus();
        if (r==0 & s==0& t==0& n==0){
            ls.setMessage( "پست بی بار است. آیا مایل به ثبت اطلاعات هستید؟");
            ls.setStatus(0);

        }
        else if((r<num_Load & s<num_Load & t<num_Load)){
            ls.setMessage( "عادی");
            ls.setStatus(0);
        }
        else if(r>num_Load & s>num_Load & t>num_Load) {
            ls.setMessage( "بار پست بحرانی است");
            ls.setStatus(1);
        }
        else if (((r+s+t)/3)>num_Load & ((r+s+t)/3)<(num_Load*1.1)){
            ls.setMessage( " بار پست بیش از حدمجاز و نامتعادل است");
            ls.setStatus(2);
        }
        else if (((r+s+t)/3)>num_Load){
            ls.setMessage( "بار پست بحرانی و نامتعادل است");
            ls.setStatus(3);
        }
        else {
            ls.setMessage( "بار پست نا متعادل است");
            ls.setStatus(4);
        }
        return ls;

    }

    public static int getNominalLoad(int cap) {
        double loadCoefficient = 1.44;
        double weatherCoefficient = .98;
        double heightCoefficient = .98;

        return (int) (loadCoefficient *weatherCoefficient*heightCoefficient* cap);
    }
}
