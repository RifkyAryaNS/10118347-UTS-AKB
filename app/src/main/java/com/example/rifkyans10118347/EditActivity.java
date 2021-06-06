package com.example.rifkyans10118347;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    DBHandler handler;
    EditText Editjudul, Editcategory, Editisi;
    Button simpan;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.setTitle("Form Edit");

        handler = new DBHandler(this);
        id = getIntent().getLongExtra(DBHandler.row_id, 0);

        Editjudul = (EditText) findViewById(R.id.editJudul);
        Editcategory = (EditText) findViewById(R.id.editCategory);
        Editisi = (EditText) findViewById(R.id.editIsi);

        getData();

        simpan = (Button) findViewById(R.id.btnSimpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Judul = Editjudul.getText().toString().trim();
                String Category = Editcategory.getText().toString().trim();
                String Isi = Editisi.getText().toString().trim();

                if (Judul.equals("") || Category.equals("") || Isi.equals("")) {
                    Toast.makeText(EditActivity.this, "Pastikan form sudah terisi", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(DBHandler.row_judul, Judul);
                    values.put(DBHandler.row_category, Category);
                    values.put(DBHandler.row_isi, Isi);
                    handler.updateData(values, id);
                    finish();
                }
            }
        });

    }

    private void getData() {
        Cursor cursor = handler.satuData(id);
        if (cursor.moveToFirst()) {
            String Judul = cursor.getString(cursor.getColumnIndex(DBHandler.row_judul));
            String Category = cursor.getString(cursor.getColumnIndex(DBHandler.row_category));
            String Isi = cursor.getString(cursor.getColumnIndex(DBHandler.row_isi));
            Editjudul.setText(Judul);
            Editcategory.setText(Category);
            Editisi.setText(Isi);
        }
    }
}