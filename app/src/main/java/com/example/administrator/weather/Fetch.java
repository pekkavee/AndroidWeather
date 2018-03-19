package com.example.administrator.weather;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 16.3.2018.
 */

public class Fetch extends AsyncTask<Void, Void, Void> {
    String data="";
    String dataParsed="";
    String singleParsed="";
    String kaupunki;
    String code="FI";


    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            URL url =new URL("http://api.openweathermap.org/data/2.5/weather?q="+kaupunki+","+code+"&appid=b6790c074a13e60070969fb5214f087e&units=metric");
            HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            InputStream inputStream =httpURLConnection.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while (line !=null) {
                line=bufferedReader.readLine();
                data=data+line;
            }

            JSONObject jsonObject = new JSONObject(data);
            String name = (String) jsonObject.get("name");
            JSONObject technology = (JSONObject) jsonObject.get("main");
            JSONArray jsonarr_1 = (JSONArray) jsonObject.get("weather");
            String desc="";
            for(int i=0;i<jsonarr_1.length();i++)
            {
            //find description
                JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
                desc=(String)jsonobj_1.get("description");
            }


            try {
                double temp = (double) technology.get("temp");
                if (temp > 0) {
                    data = "" + name + "\n" +"+" +temp + "°C\n" + desc;
                } else {
                    data = "" + name + "\n" + temp + "°C\n" + desc;
            }
            } catch (ClassCastException e){
                int temp =(int)technology.get("temp");
                if(temp>0) {
                    data = "" + name + "\n" + "+" + temp + "°C\n" + desc;
                }else {
                    data = "" + name + "\n" + temp + "°C\n" + desc;
                }

            }



            } catch (IOException e1) {
            e1.printStackTrace();

    } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.data);
    }
}
