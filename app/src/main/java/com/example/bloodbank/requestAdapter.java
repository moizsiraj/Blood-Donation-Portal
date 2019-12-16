package com.example.bloodbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class requestAdapter extends RecyclerView.Adapter<requestAdapter.RequestViewHolder> {

    private ArrayList<requestCardItems> requestCard;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        //void onItemClick(int position);
        void onAcceptClick(int position);
        void onRejectClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_card,parent,false);
        RequestViewHolder rvh = new RequestViewHolder(v, listener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        requestCardItems currentCard = requestCard.get(position);
        holder.name.setText(currentCard.getName());
        holder.bloodBank.setText(currentCard.getBloodBank());
        holder.branch.setText(currentCard.getBranch());
        holder.date.setText(currentCard.getDate());
        holder.time.setText(currentCard.getTime());
    }

    @Override
    public int getItemCount() {
        return requestCard.size();
    }

    public requestAdapter(ArrayList<requestCardItems> requestCard){

        this.requestCard = requestCard;
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView bloodBank;
        public TextView branch;
        public TextView date;
        public TextView time;
        public Button accept;
        public Button reject;


        public RequestViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.acceptScrNameTxt);
            bloodBank = itemView.findViewById(R.id.acceptScrBloodBankTxt);
            branch = itemView.findViewById(R.id.acceptScrBranchTxt);
            date = itemView.findViewById(R.id.acceptScrDateTxt);
            time = itemView.findViewById(R.id.acceptScrTime);
            accept = itemView.findViewById(R.id.acceptRequest);
            reject = itemView.findViewById(R.id.rejectRequest);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if  (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onAcceptClick(position);
                        }
                    }
                }
            });

            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if  (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onRejectClick(position);
                        }
                    }
                }
            });
        }
    }
}
