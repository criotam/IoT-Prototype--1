package com.logui.arghasen.criotam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FilesRecyclerAdapter extends RecyclerView.Adapter<FilesRecyclerAdapter.FilesRecyclerViewHolder> {

    Context context;

    ArrayList<FilesPOJO> arrayList;

    ClickListener clickListener;

    public interface ClickListener{
        public void onFileClickListener(View view, int position, ArrayList<String> file_url);
    }

    public FilesRecyclerAdapter(Context context, ArrayList<FilesPOJO> arrayList, ClickListener clickListener){
        this.clickListener = clickListener;
        this.context= context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FilesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_element, parent, false);
        return new FilesRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilesRecyclerViewHolder holder, int position) {

        Log.d("bind", ""+position);

        holder.date.setText(arrayList.get(position).getDate()+"");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class FilesRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView date;

        public FilesRecyclerViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   clickListener.onFileClickListener(view, getAdapterPosition(), arrayList.get(getAdapterPosition()).getFileUrl());
                }
            });

        }
    }


}
