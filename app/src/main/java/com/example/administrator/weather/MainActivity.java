package com.example.administrator.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    Button click;
    public static TextView data;


    EditText city;
    EditText code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=(Button)findViewById(R.id.button);
        data=(TextView)findViewById(R.id.fetcheddata);
        city=(EditText)findViewById(R.id.city);
        code=(EditText)findViewById(R.id.country);


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
