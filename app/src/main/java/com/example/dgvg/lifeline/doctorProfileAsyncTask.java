package com.example.dgvg.lifeline;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by DGVG on 1/3/2016.
 */
public class doctorProfileAsyncTask extends AsyncTask<ArrayList<String>,Integer,HashMap<String,String>> {
    String name;
    String page;
    doctorProfileAsyncTaskInterface listener;

    @Override
    protected HashMap<String,String> doInBackground(ArrayList<String>... params) {
        name = params[0].get(0);
        page=params[0].get(1);
        try {
            URL url = new URL("\"172.20.10.5/PHP/AR.PHP\"");
            //Toast.makeText(getContext(), " www.kvsc.in/mobile_app/get_doctors/?page=1", Toast.LENGTH_SHORT).show();
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream data = urlConnection.getInputStream();
            Scanner s = new Scanner(data);
            StringBuffer output = new StringBuffer();
            while (s.hasNext()) {
                output.append(s.nextLine());
            }
            Log.i("output", output.toString());
            s.close();
            urlConnection.disconnect();
            return parseJson(output.toString());


        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

    }

    private HashMap<String,String> parseJson(String jsonString) {
        HashMap<String,String> ans=new HashMap<>();
        try {
            JSONArray array = new JSONArray(jsonString);

            for (int i = 0; i < array.length(); i++) {

                JSONObject a = array.getJSONObject(i);
                if(name.equals(a.getString("name"))) {
                    ans.put("name",a.getString("name"));
                    ans.put("doctor_id",a.getString("doctor_id"));
                    ans.put("profile_picture", "http://www.kvsc.in"+a.getString("profile_picture"));
                    ans.put("experience",a.getString("experience"));
                    JSONArray qualifications = a.getJSONArray("qualifications");
                    for(int k=0;k<qualifications.length();k++){
                        JSONObject jq = qualifications.getJSONObject(k);
                        ans.put("degree"+k+"",jq.getString("degree")+","+jq.getString("institute"));
                    }
                    JSONArray labs_timings = a.getJSONArray("labs_timings");
                    JSONObject jo = labs_timings.getJSONObject(0);
                    ans.put("consultfee",jo.getString("opd_fees"));
                    JSONArray doctor_timings=jo.getJSONArray("doctor_timings");
                    for(int j=0;j<doctor_timings.length();j++){
                        JSONObject jd = doctor_timings.getJSONObject(j);
                        ans.put("day"+jd.getString("day"),jd.getString("start_time")+"-"+jd.getString("end_time"));
                    }

                }
            }

        } catch (JSONException e) {
            //      Toast.makeText(getContext(),"Wrong",Toast.LENGTH_SHORT).show();
        }
        return ans;

    }

    @Override
    protected void onPostExecute(HashMap<String,String> s) {
        super.onPostExecute(s);
        if(listener!=null){
            listener.onReturn(s);
        }
    }
}