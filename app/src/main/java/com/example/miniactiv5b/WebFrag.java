package com.example.miniactiv5b;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebFrag extends Fragment {

    private View rootView;
    private Handler handler;

    private TextView textWeb;

    public WebFrag(){

    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        this.textWeb = rootView.findViewById(R.id.webTextView);
        this.handler = new Handler();

        try {
            final Thread tr = new Thread() {
                public void run() {
                    try {
                        URL url = new URL("https://www.udl.cat/");
                        URLConnection conn = url.openConnection();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        StringBuilder total = new StringBuilder();
                        for(String line ; (line = rd.readLine()) != null ;){
                            total.append(line).append('\n');
                        }
                        WebFrag.this.handler.post(new SetText(total.toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            tr.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.webfrag, container, false);
        return rootView;
    }

    class SetText implements Runnable{
        private String text;

        public SetText(String msg){
            this.text = msg;
        }
        @Override
        public void run() {
            textWeb.setText(text);
        }
    }
}
