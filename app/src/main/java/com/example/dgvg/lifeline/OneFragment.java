package com.example.dgvg.lifeline;

/**
 * Created by DGVG on 12/22/2015.
 */

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {
    public OneFragment() {
        // Required empty public constructor
    }
    boolean flag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag=true;
    }


    private List<String> clinics=new ArrayList<>();
    MyRecyclerViewAdapter adapter;
    private RecyclerView mRecyclerView;


    int page=4;
    private static String LOG_TAG = "RecyclerViewActivity";
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_one, container, false);
      //  v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
        initializeData1();
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        adapter = new MyRecyclerViewAdapter(getActivity(), clinics,mRecyclerView, new ClickListener() {
            @Override
            public void onItemClick(View v, int position) {


//                // do what ever you want to do with it
            }
        });
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//            adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
//                @Override
//                public void onLoadMore(int page) {
//                    if(page<=10)
//                    new OneFragmentAsyncTask(OneFragment.this).execute(page++);
//                }
//            });
            return v;
    }




    public void initializeData1(){
        clinics.add("Lion Blood Bank");
        clinics.add("Royal Blood Bank");
        clinics.add("Army Blood Bank");
        clinics.add("AIIMS Blood Bank");




    }



}




