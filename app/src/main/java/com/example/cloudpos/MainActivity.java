package com.example.cloudpos;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Find the TextInputLayout and Button in the layout
        TextInputLayout usernameLayout = findViewById(R.id.usernameField);
        TextInputLayout passwordLayout = findViewById(R.id.passwordField);
        Button loginButton = findViewById(R.id.loginButton);

        // Get EditText from TextInputLayout
        EditText usernameEditText = usernameLayout.getEditText();
        EditText passwordEditText = passwordLayout.getEditText();

        // Set an OnKeyListener for username field
        if (usernameEditText != null) {
            usernameEditText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    // Move focus to password field
                    passwordEditText.requestFocus();
                    return true;
                }
                return false;
            });
        }

        // Set an OnKeyListener for password field
        if (passwordEditText != null) {
            passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Trigger button click
                    loginButton.performClick();
                    return true;
                }
                return false;
            });
        }

        // Set an OnClickListener for the button
        loginButton.setOnClickListener(v -> {
            // Get text from EditText fields
            String username = usernameEditText != null ? usernameEditText.getText().toString() : "";
            String password = passwordEditText != null ? passwordEditText.getText().toString() : "";

            // Handle login action with the retrieved values
            handleLogin(username, password);
        });

        // Optional: Apply window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to handle login with provided credentials
    private void handleLogin(String username, String password) {
        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            // Create an Intent to start DashboardActivity
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Show an error message if the credentials are incorrect
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

}
