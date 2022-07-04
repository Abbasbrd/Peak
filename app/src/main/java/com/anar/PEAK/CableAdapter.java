package com.anar.PEAK;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CableAdapter extends ArrayAdapter
{
    private Context viewcontext;
    private int layoutRes;
    public CableAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        viewcontext = context;
        layoutRes= resource;

    }

    public CableAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public CableAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    public CableAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public CableAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    public CableAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        if (convertView==null)
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_spinner,
//                    parent,
//                    false);
        Spinner cableSizeSpinner = convertView.findViewById(layoutRes);



        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(viewcontext,
//                R.array.CableSize, R.layout.layout_spinner
//        );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(viewcontext,
                R.array.CableSize, android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears
        // adapter.setDropDownViewResource(R.layout.layout_spinner);

        // Apply the adapter to the spinner
        cableSizeSpinner.setAdapter(adapter);

        return convertView;
    }
}
