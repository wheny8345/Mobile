package com.example.namasayawheny.hero_ex;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Hero extends Activity {
    protected ListView lv;
    protected ListAdapter adapter;
    SQLiteDatabase db;
    Cursor cursor;
    EditText et_db;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_hero);

        db = (new DB_Hero(this)).getWritableDatabase();
        lv = (ListView) findViewById(R.id.lv);
        et_db = (EditText) findViewById(R.id.et);

        try {
            cursor = db.rawQuery("SELECT * FROM Hero ORDER BY nama ASC", null);
            adapter = new SimpleCursorAdapter(this, R.layout.isi_lv, cursor,
                    new String[] { "nama", "biografi", "img" }, new int[] {
                    R.id.tv_nama, R.id.tvBiografi, R.id.imV });
            lv.setAdapter(adapter);
            lv.setTextFilterEnabled(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    detail(position);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Mencari Data dari database
    public void search_db(View v) {
        String edit_db = et_db.getText().toString();
        if (!edit_db.equals("")) {
            try {
                cursor = db.rawQuery("SELECT * FROM Hero WHERE nama LIKE ?",
                        new String[] { "%" + edit_db + "%" });
                adapter = new SimpleCursorAdapter(
                        this,
                        R.layout.isi_lv,
                        cursor,
                        new String[] { "nama", "biografi", "img" },
                        new int[] { R.id.tv_nama, R.id.tvBiografi, R.id.imV });
                if (adapter.getCount() == 0) {
                    Toast.makeText(
                            this,
                            "Tidak ditemukan data dengan kata kunci " + edit_db
                                    + "", Toast.LENGTH_SHORT).show();
                } else {
                    lv.setAdapter(adapter);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                cursor = db.rawQuery("SELECT * FROM Hero ORDER BY nama ASC",
                        null);
                adapter = new SimpleCursorAdapter(
                        this,
                        R.layout.isi_lv,
                        cursor,
                        new String[] { "nama", "biografi", "img" },
                        new int[] { R.id.tv_nama, R.id.tvBiografi, R.id.imV });
                lv.setAdapter(adapter);
                lv.setTextFilterEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void detail(int position) {
        int im = 0;
        String _id = "";
        String nama = "";
        String biografi = "";
        if (cursor.moveToFirst()) {
            cursor.moveToPosition(position);
            im = cursor.getInt(cursor.getColumnIndex("img"));
            nama = cursor.getString(cursor.getColumnIndex("nama"));
            biografi = cursor.getString(cursor.getColumnIndex("biografi"));

        }

        Intent iIntent = new Intent(this, DB_Call.class);
        iIntent.putExtra("dataIM", im);
        iIntent.putExtra("dataNama", nama);
        iIntent.putExtra("dataBiografi", biografi);
        setResult(RESULT_OK, iIntent);
        startActivityForResult(iIntent, 99);
    }
}
