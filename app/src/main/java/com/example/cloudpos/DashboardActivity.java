package com.example.cloudpos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        ImageView userProfile = findViewById(R.id.userProfile);

        userProfile.setOnClickListener(v -> {
            // Create a PopupMenu
            Intent intent = new Intent(DashboardActivity.this, UserProfile.class);
            startActivity(intent);
            finish();
        });
    }
}
