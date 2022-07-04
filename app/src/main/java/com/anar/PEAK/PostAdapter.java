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

public class PostAdapter extends ArrayAdapter<Post> {
    public PostAdapter(@NonNull Context context, int resource, @NonNull List objects) {
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
        int n=0;




        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemview,parent,false);
        }
        TextView pCode = convertView.findViewById(R.id.postCode);
        TextView pCap =convertView.findViewById(R.id.postCap);
        TextView tvR =convertView.findViewById(R.id.r);
        TextView tvS =convertView.findViewById(R.id.s);
        TextView tvT =convertView.findViewById(R.id.t);
        TextView tvN =convertView.findViewById(R.id.n);

        pCode.setText(postCode);
        pCap.setText(Integer.toString(postCap));
        if (p.getLoad()!=null)
        {
            PostLoad l = p.getLoad();
            r = l.getPhaseR();
            s = l.getPhaseS();
            t = l.getPhaseT();
            n = l.getNutralN();
            tvR.setText(Integer.toString(r));
            tvS.setText(Integer.toString(s));
            tvT.setText(Integer.toString(t));
            tvN.setText(Integer.toString(n));
        }



        return  convertView;
    }


}
