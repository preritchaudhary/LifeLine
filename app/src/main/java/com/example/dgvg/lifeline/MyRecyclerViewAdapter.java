package com.example.dgvg.lifeline;

/**
 * Created by DGVG on 12/24/2015.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.PersonViewHolder> {
    Context mContext;
    ClickListener clickListener;
    private List<String> doctors;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private int visibleThreshold = 3;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private int pageno=4;
    private OnLoadMoreListener onLoadMoreListener;

    MyRecyclerViewAdapter(Context mContext, List<String> doctors, RecyclerView recyclerView, ClickListener clickListener) {
        this.doctors = doctors;
        this.mContext = mContext;
        this.clickListener = clickListener;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore(pageno++);
                                }
                                loading=true;

                            }
                        }
                    });
        }
    }
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }
    public void setLoaded() {
        loading = false;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        final PersonViewHolder pvh = new PersonViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v, pvh.getPosition());
            }
        });
        return pvh;
    }


    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.clinic.setText(doctors.get(position).toString());
//        holder.doctorSpeciality.setText(doctors.get(position).speciality);
//        Picasso.with(mContext)
//                .load(doctors.get(position).photo)
//                .into(holder.iv);
//

    }



    @Override
    public int getItemCount() {
        return doctors.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemViewType(int position) {
        return doctors.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView clinic;

        PersonViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            clinic = (TextView) itemView.findViewById(R.id.clinic);
//            doctorSpeciality = (TextView) itemView.findViewById(R.id.person_age);
//            iv = (ImageView) itemView.findViewById(R.id.person_photo);

        }

    }
}