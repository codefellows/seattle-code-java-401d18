package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;

public class LoginActivity extends AppCompatActivity {
  private static String TAG = "LoginActivity";

  Button submitButton;
  EditText emailEditText;
  EditText passwordEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    emailEditText = findViewById(R.id.LoginActivityEmailEditText);
    passwordEditText = findViewById(R.id.LoginActivityPasswordEditText);
    submitButton = findViewById(R.id.LoginActivtySubmitButton);

    setupSubmitButton();
  }

  void setupSubmitButton() {
    //Cognito Login Logic
    submitButton.setOnClickListener(v -> {
      Amplify.Auth.signIn(emailEditText.getText().toString(),
        passwordEditText.getText().toString(),
        success -> {
          Log.i(TAG, "Login succeeded: " + success.toString());
          Intent goToMainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
          startActivity(goToMainActivityIntent);
        },
        failure -> Log.i(TAG, "Login failed: " + failure.toString())
      );
    });
  }
}
