package com.example.da08.serverconnection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.da08.serverconnection.domain.Bbs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Da08 on 2017. 6. 30..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
        List<Bbs> list = new ArrayList<>();

        public void setData(List<Bbs> list){
            this.list = list;
        }
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbs_list,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Bbs bbs = list.get(position);
            holder.id.setText(bbs.id+"");   //  int타입이므로 "" 붙여줘야 함
            holder.title.setText(bbs.title);
            holder.author.setText(bbs.author);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Holder extends RecyclerView.ViewHolder{

            TextView id, title, author;

            public Holder(View itemView){
                super(itemView);
                id = (TextView)itemView.findViewById(R.id.id);
                title = (TextView)itemView.findViewById(R.id.title);
                author = (TextView)itemView.findViewById(R.id.author);
            }
        }
}
