package com.example.administrator.weather;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button click;
    public static TextView data;
    Spinner spinner;
    ArrayAdapter<CharSequence>adapter;
    Map<String, String>codes;




    EditText city;
    EditText code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=(Button)findViewById(R.id.button);
        codes=new HashMap<>();
        codes.put("Helsinki","FI");
        codes.put("Espoo","FI");
        codes.put("Lahti","FI");
        codes.put("Savonlinna","FI");
        codes.put("Stockholm","SWE");
        codes.put("Vantaa","FI");
        codes.put("Tallinn", "EE");




        data=(TextView)findViewById(R.id.fetcheddata);
        city=(EditText)findViewById(R.id.city);
        code=(EditText)findViewById(R.id.country);
        spinner=(Spinner)findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this, R.array.city_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);




       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String city2=spinner.getSelectedItem().toString();
                 city.setText(city2);
               code.setText(codes.get(city2));
               Fetch process=new Fetch();



              // process.setKaupunki(city.getText().toString());
               process.setKaupunki(city2);
               if(codes.get(city2).length()>0)
                   process.setCode(codes.get(city2));
               process.execute();

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });




        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fetch process=new Fetch();



                process.setKaupunki(city.getText().toString());
                if(code.getText().toString().length()>0)
                process.setCode(code.getText().toString());
process.execute();


            }
        });

    }
}
