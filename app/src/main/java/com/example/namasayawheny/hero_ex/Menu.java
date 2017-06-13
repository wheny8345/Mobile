package com.example.namasayawheny.hero_ex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity implements View.OnClickListener{
    Button tokoh, tentang, bantuan, kuis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tokoh = (Button) findViewById(R.id.bTokoh);
        tentang = (Button) findViewById(R.id.bTentang);
        bantuan = (Button) findViewById(R.id.bBantuan);
        kuis = (Button) findViewById(R.id.bKuis);

        tokoh.setOnClickListener(this);
        tentang.setOnClickListener(this);
        bantuan.setOnClickListener(this);
        kuis.setOnClickListener(this);

    }
    //Untuk menyambungkan satu menu ke menu yg lain dengan intent
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.bTokoh:
                Intent a = new Intent(Menu.this, Hero.class);
                startActivity(a);
                break;
            case R.id.bTentang:
                Intent d = new Intent(Menu.this, Tentang.class);
                startActivity(d);
                break;
            case R.id.bBantuan:
                Intent e = new Intent(Menu.this, Bantuan.class);
                startActivity(e);
                break;
            case R.id.bKuis:
                Intent f = new Intent(Menu.this, Kuis.class);
                startActivity(f);
                break;
        }

    }

    //Untuk keluar dari app
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

    }
}
