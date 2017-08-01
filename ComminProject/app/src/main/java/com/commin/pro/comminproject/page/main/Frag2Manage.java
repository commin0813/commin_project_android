package com.commin.pro.comminproject.page.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.commin.pro.comminproject.R;
import com.victor.loading.rotate.RotateLoading;

/**
 * Created by user on 2017-07-25.
 */
public class Frag2Manage extends Fragment {
    LinearLayout layout;
    public Frag2Manage(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (LinearLayout)inflater.inflate(R.layout.page_main_frag_manage,container,false);
        init();
        return layout;
    }


    Button btn_progress;
    RotateLoading rotateLoading;
    private void init(){
        btn_progress = (Button)layout.findViewById(R.id.btn_progress);
        rotateLoading = (RotateLoading)layout.findViewById(R.id.rotateloading);
        btn_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rotateLoading.isStart()){
                    btn_progress.setText("START");
                    rotateLoading.stop();
                }else {
                    btn_progress.setText("STOP");
                    rotateLoading.start();
                }
            }
        });
    }
}
