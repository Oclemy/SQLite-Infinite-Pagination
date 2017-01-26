package com.tutorials.hp.sqliteinfinitepager.mRecycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutorials.hp.sqliteinfinitepager.R;
import com.tutorials.hp.sqliteinfinitepager.mData.Spaceship;

import java.util.List;


/**
 * Created by Oclemy on 1/25/2017 for ProgrammingWizards Channel and http://www.camposha.com.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context c;
    List<Spaceship> spaceships;

    /*
    CONSTRUCTOR
     */
    public MyAdapter(Context c, List<Spaceship> spaceships) {
        this.c = c;
        this.spaceships = spaceships;
    }

    //INITIALIE VH
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    //BIND DATA
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.nametxt.setText(spaceships.get(position).getName());

    }

    /*
    TOTAL ITEMS
     */
    @Override
    public int getItemCount() {
        return spaceships.size();

    }

    /*
    ADD DATA TO ADAPTER
     */
    public void add(Spaceship s) {
        spaceships.add(s);
        notifyDataSetChanged();
    }

    /*
    CLEAR DATA FROM ADAPTER
     */
    public void clear() {
        spaceships.clear();
        notifyDataSetChanged();
    }

    /*
    VIEW HOLDER CLASS
     */
    class MyHolder extends RecyclerView.ViewHolder {

        TextView nametxt;

        public MyHolder(View itemView) {
            super(itemView);

            this.nametxt = (TextView) itemView.findViewById(R.id.nameTxt);

        }
    }

}