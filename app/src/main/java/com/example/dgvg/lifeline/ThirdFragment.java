package com.example.dgvg.lifeline;

/**
 * Created by DGVG on 12/22/2015.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class ThirdFragment extends Fragment  {

    public ThirdFragment() {
        // Required empty public constructor
    }
    String s="";
    SharedPreferences token;
    public static final String PREFS_NAME1 = "MyPrefs";
    boolean islogin=false;
    public static final String PREFS_NAME = "MyPrefs";
    TextView tv1;
    TextView logout;
    EditText username;
    EditText password;
    View view;
    ViewGroup container;
    SharedPreferences settings;
    LayoutInflater inflater;

    static FirstPageFragmentListener firstPageListener;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings =getActivity().getSharedPreferences(PREFS_NAME, 0);
       // token=getActivity().getSharedPreferences(PREFS_NAME1,0);

    }

    @Override
    public void onResume() {
        super.onResume();
        settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("login", false);
        if(silent){

        }else {
            tv1.setText("");
            username.setText("");
            password.setText("");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         this.container=container;
            view = inflater.inflate(R.layout.fragment_three, container, false);
          //  view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
//            username = (EditText) view.findViewById(R.id.username);
//            password = (EditText) view.findViewById(R.id.password);
//            tv1 = (TextView) view.findViewById(R.id.msg);
//            final HashMap<String, String> complete = new HashMap<>();
//            TextView tv = (TextView) view.findViewById(R.id.signup);
//            Button b = (Button) view.findViewById(R.id.login);
//            final String tokens=settings.getString("token","");
//            //Toast.makeText(getActivity(), tokens, Toast.LENGTH_SHORT).show();
//            b.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    complete.put("username", username.getText().toString());
//                    complete.put("password", password.getText().toString());
//                    if (tokens.length() != 0) {
//                        complete.put("token", tokens);
//                    }
////                    LoginAsyncTask lat = new LoginAsyncTask();
////                    lat.listener = ThirdFragment.this;
////                    lat.execute(complete);
//                }
//            });
//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent in = new Intent();
//                    in.setClass(getContext(), SignupActivity.class);
//                    startActivity(in);
//                }
//            });

        return view;

    }


}
