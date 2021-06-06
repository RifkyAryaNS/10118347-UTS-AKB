package com.example.rifkyans10118347;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setTitle("PROFILE");

        BottomNavigationView bottom = findViewById(R.id.nav_bar);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_person :
                        Intent profile = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(profile);
                        finish();
                        break;
                    case R.id.nav_home :
                        Intent home = new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.nav_info :
                        Intent info = new Intent(ProfileActivity.this, InfoActivity.class);
                        startActivity(info);
                        finish();
                        break;
                }

                return true;
            }
        });

    }
}