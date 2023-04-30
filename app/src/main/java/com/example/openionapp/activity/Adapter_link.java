package com.example.openionapp.activity;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openionapp.R;
import com.example.openionapp.model.RecentLinkModel;
import com.example.openionapp.model.ResponseModel;
import java.util.ArrayList;


public class Adapter_link extends RecyclerView.Adapter<Adapter_link.ViewHolder> {

    private final String TAG = Adapter_link.class.getSimpleName();
    private ArrayList<RecentLinkModel> responseModelArrayList;
    private Activity activity;
    public Adapter_link(Activity activity, ArrayList<RecentLinkModel> getresponselist) {
        this.activity = activity;
        this.responseModelArrayList = getresponselist;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, TAG + "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contactView = inflater.inflate(R.layout.adapter_link, parent, false);
        Adapter_link.ViewHolder viewHolder = new Adapter_link.ViewHolder(contactView);
          Log.e(" list count", ""+ responseModelArrayList.size());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_link.ViewHolder holder, int position) {
        Log.d(TAG, TAG + "onBindViewHolder");
        holder.txt_title.setText(responseModelArrayList.get(position).getTitle());
        holder.txt_totalclick.setText(responseModelArrayList.get(position).getTotal_clicks());
        holder.txt_date.setText(responseModelArrayList.get(position).getCreated_at());
        holder.txt_url.setText(responseModelArrayList.get(position).getWeb_link());

    }


    public void update(ArrayList<ResponseModel> appointmentlist) {
        appointmentlist.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
//         Log.d(TAG,TAG+"Array size::"+responseModelArrayList.size());
        if (responseModelArrayList.size()!=0){

        }
        return responseModelArrayList.size();

    }

    public void clear() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView img_orignl,txt_title,txt_totalclick,txt_date,txt_url;
        ImageView img_copy;


        public ViewHolder(View itemView) {
            super(itemView);
            img_orignl = itemView.findViewById(R.id.img_orignl);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_totalclick = itemView.findViewById(R.id.txt_totalclick);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_url= itemView.findViewById(R.id.txt_url);
            img_copy= itemView.findViewById(R.id.img_copy);
            img_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, "Text copied", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }



}