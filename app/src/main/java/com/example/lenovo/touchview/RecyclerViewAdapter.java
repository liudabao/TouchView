package com.example.lenovo.touchview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2016/6/17.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.Holder>{

    Context context;
    List<String> list;
    public RecyclerViewAdapter(Context context, List<String> list){
        this.context=context;
        this.list=list;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_item, parent, false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        String data=list.get(position);
        holder.mTextView.setText(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public Holder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View view){
            mTextView=(TextView)view.findViewById(R.id.textView);
        }

    }
}
