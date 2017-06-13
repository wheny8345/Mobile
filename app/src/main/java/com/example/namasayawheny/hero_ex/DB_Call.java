package com.example.namasayawheny.hero_ex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;



public class DB_Call extends Activity {
    ImageView Im;
    TextView tv_nama, tv_biografi, id;
    Gallery gallery;
    ImageSwitcher imageSwitcher;
    Integer[] imageIDs = new Integer[3];
    int msg_im;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_hero);

        Intent iIdentifikasi = getIntent();
        msg_im = iIdentifikasi.getIntExtra("dataIM", 0);
        String msg_nama = iIdentifikasi.getStringExtra("dataNama");
        String msg_biografi = iIdentifikasi.getStringExtra("dataBiografi");
        Im = (ImageView) findViewById(R.id.iv_detail);
        tv_nama = (TextView) findViewById(R.id.tvNama);
        tv_biografi = (TextView) findViewById(R.id.tvBiografi);
        Im.setImageResource(msg_im);
        tv_nama.setText(msg_nama);
        tv_biografi.setText(msg_biografi);
    }
}