package com.ej1.iedeveloper.ej1.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ej1.iedeveloper.ej1.R;
import com.ej1.iedeveloper.ej1.model.RecyclerViewTestItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 28/12/16.
 */

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.ViewHolder> {

    private List<RecyclerViewTestItem> items;
    private Context context;

    public TestRecyclerAdapter(List<RecyclerViewTestItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        ViewHolder holder=new ViewHolder(viewItem);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(items.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @Nullable  @BindView(R.id.textRecyclerItem)
        TextView text;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
