package com.hog2020.ex35threadprogress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    ProgressDialog dialog;
    int gauge=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {

        if(dialog!=null)return;
        //while type progress dialog
        dialog=new ProgressDialog(this);
        dialog.setTitle("wheel dialog");
        dialog.setMessage("downloading....");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        //3초뒤에 dialog 종료(dismiss)
        handler.sendEmptyMessageDelayed(0,3000);

        dialog.setCanceledOnTouchOutside(false);
    }

    public void clickBtn2(View view) {
        if(dialog!=null)return;

        //bar type progress
        dialog= new ProgressDialog(this);
        dialog.setTitle("막대 다이아로그");
        dialog.setMessage("downloading");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        dialog.setProgress(gauge);

        Thread t =new Thread(){
            @Override
            public void run() {
                gauge=0;
                while(gauge<=100){
                    gauge++;
                    dialog.setProgress(gauge);

                    //0.05초 대기
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                dialog.dismiss();
                dialog=null;
            }
        };
        t.start();
    }

    Handler handler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            dialog.dismiss();
            dialog =null;
        }
    };


}