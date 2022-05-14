package net.jo.pricetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

//import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.MyViewHolder>{

    private List<Price> userBeanList;
    private Context mContext;

    // Constructor
    public PriceAdapter(List<Price> userBeanList, Context context) {
        this.userBeanList = userBeanList;
        this.mContext = context;

        Collections.reverse(this.userBeanList);
        // add title line's data
        this.userBeanList.add(0, new Price("", "數量", 0));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.row_main, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        long timestamp = userBeanList.get(position).getT();
        if (timestamp == 0) {
            // First line Title
            holder.timeTV.setText("時間");
            holder.qTV.setMinimumHeight(80);
//            holder.qTV.setGravity(Gravity.RIGHT);
            holder.priceTV.setText("價格");
            holder.qTV.setText(userBeanList.get(position).getQ());
        } else {
            String str = new Timestamp(timestamp).toString();
            holder.timeTV.setText(str.substring(10, 19));
            holder.priceTV.setText(userBeanList.get(position).getP().substring(0, 8));
            holder.qTV.setText(userBeanList.get(position).getQ().substring(0, 8));
        }

    }

    @Override
    public int getItemCount() {
        return userBeanList.size();
    }

    // View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView timeTV, priceTV, qTV;

        public MyViewHolder(View itemView) {
            super(itemView);

            timeTV = (TextView) itemView.findViewById(R.id.timeTV);
            priceTV = (TextView) itemView.findViewById(R.id.priceTV);
            qTV = (TextView) itemView.findViewById(R.id.qTV);

        }
    }



}
