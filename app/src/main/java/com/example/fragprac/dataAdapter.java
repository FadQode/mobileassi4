package com.example.fragprac;

import static android.app.PendingIntent.getActivity;
import static android.content.ContentValues.TAG;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder> {

    static Context context;
    ArrayList<Pelanggan> list;

    public dataAdapter(Context context, ArrayList<Pelanggan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public dataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.data_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull dataAdapter.MyViewHolder holder, int position) {
        Pelanggan pelanggan = list.get(position);
        Log.d(TAG, "onBindViewHolder: "+ pelanggan.getName());
//        System.out.println(pelanggan.getName() + pelanggan.getAddress() + pelanggan.getPhone());
        holder.nameHolder.setText(pelanggan.getName());
        holder.addressHolder.setText(pelanggan.getAddress());
        holder.phoneHolder.setText(pelanggan.getPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameHolder, addressHolder, phoneHolder;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameHolder = itemView.findViewById(R.id.textName);
            addressHolder = itemView.findViewById(R.id.textAddress);
            phoneHolder = itemView.findViewById(R.id.textPhone);

            phoneHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create intent to open WhatsApp with a specific phone number
                    MainActivity mainActivityInstance = new MainActivity();

                    // Create intent to open WhatsApp with a specific phone number
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + phoneHolder.getText().toString()));

                    context.startActivity(intent);

                    // Finish the activity so it doesn't stay in the back stack


                }
            });

        }

    }

}
