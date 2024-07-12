package com.example.doctor_appointment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.doctor_appointment.EditBooking;
import com.example.doctor_appointment.R;
import com.example.doctor_appointment.model.bookingList;

import java.util.List;

public class scheduleAdapter extends ArrayAdapter<bookingList> {

    private Context context;
    private List<bookingList> bookingarraylist;
    private boolean showButtons;

    public scheduleAdapter(@NonNull Context context, List<bookingList> bookingarraylist, boolean showButtons) {
        super(context, R.layout.item_appointment, bookingarraylist);

        this.context = context;
        this.bookingarraylist = bookingarraylist;
        this.showButtons = showButtons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_appointment, parent, false);
            holder = new ViewHolder();
            holder.d_photo = view.findViewById(R.id.doctor_photo);
            holder.d_name = view.findViewById(R.id.tvDoctorName);
            holder.d_speci = view.findViewById(R.id.tvSpecialization);
            holder.date = view.findViewById(R.id.tvAppointmentDate);
            holder.time = view.findViewById(R.id.tvAppointmentTime);
            holder.btnCancel = view.findViewById(R.id.btnCancel);
            holder.btnedit = view.findViewById(R.id.btnedit);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        bookingList booking = bookingarraylist.get(position);
        Glide.with(context).load(booking.getImage()).circleCrop().into(holder.d_photo);
        holder.d_name.setText(booking.getDoctorName());
        holder.d_speci.setText(booking.getSpecialization());
        holder.date.setText(booking.getDate());
        holder.time.setText(booking.getTime());
        String reason=booking.getReason();
      String b_id= String.valueOf(booking.getB_id());
        String d_id= String.valueOf(booking.getD_id());

        // Set visibility of Cancel and Reschedule buttons based on showButtons flag
        if (showButtons) {
            holder.btnCancel.setVisibility(View.VISIBLE);
            holder.btnedit.setVisibility(View.VISIBLE);
        } else {
            holder.btnCancel.setVisibility(View.GONE);
            holder.btnedit.setVisibility(View.GONE);
        }


        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), EditBooking.class);

                intent.putExtra("b_date", holder.date.getText().toString());
                intent.putExtra("b_time", holder.time.getText().toString());
                intent.putExtra("reason",reason);
                intent.putExtra("b_id",b_id);
                intent.putExtra("d_id",d_id);
                context.startActivity(intent);
            }
        });
        return view;


    }


    static class ViewHolder {
        ImageView d_photo;
        TextView d_name, d_speci, date, time;
        Button btnCancel, btnedit;
    }
    public void setShowButtons(boolean showButtons) {
        this.showButtons = showButtons;
    }
}