package com.example.rifkyans10118347;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    DBHandler handler;
    EditText judul, category, isi;
    Button btntambah;
    CheckBox cbkeepdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.setTitle("Form Add Data");

        handler = new DBHandler(this);


        judul = (EditText) findViewById(R.id.editJudul);
        category = (EditText) findViewById(R.id.editCategory);
        isi = (EditText) findViewById(R.id.editIsi);

        btntambah = (Button) findViewById(R.id.btnTambah);
        cbkeepdata = (CheckBox) findViewById(R.id.CBkeepdata);

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Judul = judul.getText().toString().trim();
                String Category = category.getText().toString().trim();
                //String Tgl = tgl.getText().toString().trim();
                String Isi = isi.getText().toString().trim();

                if (Judul.equals("") || Category.equals("") || Isi.equals("")) {
                    Toast.makeText(AddActivity.this, "Pastikan form sudah terisi", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(DBHandler.row_judul, Judul);
                    values.put(DBHandler.row_category, Category);
                    values.put(DBHandler.row_isi, Isi);
                    handler.createData(values);
                    if (!cbkeepdata.isChecked()){
                        judul.setText("");
                        category.setText("");
                        isi.setText("");
                        judul.requestFocus();
                    }
                    Toast.makeText(AddActivity.this, "Berhasil Input Data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*private String getDateToday(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String today = dateFormat.format(date);
        return today;
    }*/
}