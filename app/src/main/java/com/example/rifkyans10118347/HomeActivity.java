package com.example.rifkyans10118347;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView ls;
    DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle("My Note");

        BottomNavigationView bottom = findViewById(R.id.nav_bar);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home :
                        Intent home = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.nav_person :
                        Intent profile = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(profile);
                        finish();
                        break;
                    case R.id.nav_info :
                        Intent info = new Intent(HomeActivity.this, InfoActivity.class);
                        startActivity(info);
                        finish();
                        break;
                }

                return true;
            }
        });

        FloatingActionButton btn1 = (FloatingActionButton) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        handler = new DBHandler(this);
        ls = (ListView) findViewById(R.id.listNotes);
        ls.setOnItemClickListener(this);
        setupListView();
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Apakah Anda Ingin Keluar dari App?");
        builder.setCancelable(true);
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setupListView(){
        Cursor cursor = handler.semuaData();
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor,1);
        ls.setAdapter(customCursorAdapter);
        ls.setDividerHeight(0);
    }

   @Override
   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
       TextView getid = (TextView) view.findViewById(R.id.listID);
       final long id = Long.parseLong(getid.getText().toString());
       Cursor cur = handler.satuData(id);
       cur.moveToFirst();

       String isi = cur.getString(cur.getColumnIndex(DBHandler.row_isi));
       final String judul = cur.getString(cur.getColumnIndex(DBHandler.row_judul));
       final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
       builder.setTitle("Daftar Notes");
       builder.setMessage(isi);
       builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               dialogDeleteItem(id, judul);
           }
       });
       builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               Intent category = new Intent(HomeActivity.this, EditActivity.class);
               category.putExtra(DBHandler.row_id, id);
               startActivity(category);
           }
       });
       AlertDialog dialog = builder.create();
       dialog.show();
   }

    public void dialogDeleteItem(final long id, String judul) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(HomeActivity.this);
        builder1.setTitle("Hapus Data " + judul);
        builder1.setMessage("Apakah Anda Yakin Ingin Menghapus Data " + judul + "?");
        builder1.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                handler.deleteData(id);
                setupListView();
            }
        });
        builder1.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder1.create();
        dialog.show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        setupListView();
    }
}