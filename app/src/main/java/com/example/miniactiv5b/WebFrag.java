package com.example.miniactiv5b;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class WebFrag extends Fragment {

    private View rootView;

    public WebFrag(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getTextWeb();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.webfrag, container, false);
        return rootView;
    }

    private void getTextWeb(){
        TextView textWeb = rootView.findViewById(R.id.webTextView);
        textWeb.setText("Hola Mon");
        textWeb.setTextSize(30);
    }
}
