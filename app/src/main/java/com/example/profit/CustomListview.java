package com.example.profit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListview extends ArrayAdapter<String> {
    private String[] exerciseName;
    private String[] desc;
    private Integer[] imgid;
    private Activity context;

    public CustomListview(Activity context, String[] exerciseName, String[] desc, Integer[] imgid) {
        super(context, R.layout.listview_layout, exerciseName);

        this.context=context;
        this.exerciseName=exerciseName;
        this.desc=desc;
        this.imgid=imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout,null);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)r.getTag();
        }
        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(exerciseName[position]);
        viewHolder.tvw2.setText(desc[position]);
        return r;
    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v){
            tvw1=v.findViewById(R.id.tvExeName);
            tvw2=v.findViewById(R.id.tvExeDescription);
            ivw=v.findViewById(R.id.imageView);
        }
    }
}
