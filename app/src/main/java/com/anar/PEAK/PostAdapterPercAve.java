package com.anar.PEAK;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PostAdapterPercAve extends ArrayAdapter<Post> {
    public PostAdapterPercAve(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Post p = getItem(position);
        String postCode = p.getPostCode();
        int postCap = p.getPostCapacity();
        int r=0;
        int s=0;
        int t=0;
//        int n=0;




        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemview_load_perc_ave,parent,false);
        }
        TextView pCode = convertView.findViewById(R.id.postCode);
        TextView pCap =convertView.findViewById(R.id.postCap);
        TextView tvload_ave =convertView.findViewById(R.id.load_ave);
        TextView tvload_perc =convertView.findViewById(R.id.load_perc);

        pCode.setText(postCode);
        pCap.setText(Integer.toString(postCap));
        if (p.getLoad()!=null)
        {
            PostLoad l = p.getLoad();
            r = l.getPhaseR();
            s = l.getPhaseS();
            t = l.getPhaseT();
//            n = l.getNutralN();
            int ave = (r+s+t)/3;
            double perc = 100*ave/(postCap*1.44);

//            DecimalFormat df = new DecimalFormat("#.##");
//            String perc_s= df.format(perc);
//            tvload_perc.setText(String.valueOf(perc));

            tvload_perc.setText(String.format("%.1f",perc));
            tvload_ave.setText(String.valueOf(ave));

        }



        return  convertView;
    }


}
